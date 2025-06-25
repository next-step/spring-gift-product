package gift;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping("/product")
    public String createProduct(@RequestBody ProductDTO productdto) {
        long id = idGenerator.getAndIncrement();
        Product product = new Product(id, productdto);
        products.put(product.getId(), product);
        return "상품 생성: " + product.getId();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        return product;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        Collection<Product> productList = products.values();
        return productList;
    }

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
    public String deleteProduct(@PathVariable Long id) {
        if(!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 데이터가 존재하지 않습니다.");
        }
        products.remove(id);
        return "상품 삭제: " + id;
    }
}
