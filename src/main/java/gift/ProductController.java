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

    @GetMapping("/products")
    public String listProducts(Model model) {
        Collection<Product> productList = products.values();
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/product/add")
    public String addForm(Model model) {
        model.addAttribute("productdto", new ProductDTO());
        return "addForm";
    }

    @PostMapping("/product/add")
    public String createProduct(@ModelAttribute ProductDTO productdto) {
        long id = idGenerator.getAndIncrement();
        Product product = new Product(id, productdto);
        products.put(product.getId(), product);
        return "redirect:/api/products";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        return product;
    }

    @GetMapping("/product/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        return "updateForm";
    }

    @PatchMapping("/product/{id}/update")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO updateProductdto) {
        checkId(id);
        Product oldProduct = products.get(id);
        oldProduct.updateProduct(updateProductdto);
        return "redirect:/api/products";
    }

    @DeleteMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        checkId(id);
        products.remove(id);
        return "redirect:/api/products";
    }

    private void checkId(Long id) {
        if(!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 데이터가 존재하지 않습니다.");
        }
    }
}
