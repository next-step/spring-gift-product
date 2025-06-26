package gift;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @ResponseBody
    @PostMapping("/product")
    public Product createProduct(@RequestBody ProductDTO productdto) {
        long id = idGenerator.getAndIncrement();
        Product product = new Product(id, productdto);
        products.put(product.getId(), product);
        return product;
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        return product;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        Collection<Product> productList = products.values();
        model.addAttribute("products", productList);
        return "products";
    }

    @ResponseBody
    @PatchMapping("/product/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO updateProductdto) {
        if(!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 데이터가 존재하지 않습니다.");
        }
        Product oldProduct = products.get(id);
        oldProduct.updateProduct(updateProductdto);
        return oldProduct;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if(!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 데이터가 존재하지 않습니다.");
        }
        products.remove(id);
    }
}
