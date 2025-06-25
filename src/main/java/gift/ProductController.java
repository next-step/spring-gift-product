package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")

public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    @PostMapping
    public Product addProducts(@RequestBody Product product) {
        Long id = idGenerator.getAndDecrement();
        product.setId(id);
        products.put(id, product);
        return product;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable Long id, @RequestBody Product update) {
        Product tempId = products.get(id);
        if (tempId == null) {
            return ResponseEntity.notFound().build();
        }
        update.setId(id);
        products.put(id, update);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        if (products.remove(id) != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
