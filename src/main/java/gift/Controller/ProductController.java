package gift.Controller;

import gift.Model.Product;
import gift.Validation.ProductValidator;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public ProductController() {
        products.put(nextId, new Product(
                nextId++,
                "아이스 카페 아메리카노 T",
                4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        ));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Map<String, String> errors = ProductValidator.validate(product);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        product.setId(nextId++);
        products.put(product.getId(), product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        Product existing = products.get(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Map<String, String> errors = ProductValidator.validate(updated);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setImgURL(updated.getImgURL());
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        products.remove(id);
        return ResponseEntity.noContent().build();
    }
}
