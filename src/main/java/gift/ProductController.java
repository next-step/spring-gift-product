package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/product")
    public String createProduct(@RequestBody Product product) {
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
}
