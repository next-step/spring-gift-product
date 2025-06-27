package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")

public class ProductController {
    private final ProductStorage products;

    public ProductController(ProductStorage products) { this.products = products; }

    @GetMapping
    public List<Product> getProducts() {
        return new ArrayList<>(products.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable Long id) {
        Product storedProduct = products.findById(id);
        if (storedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(storedProduct);
    }

    @PostMapping
    public Product addProducts(@RequestBody Product product) {
        return products.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable Long id, @RequestBody Product update) {
        if (products.findById(id)== null) {
            return ResponseEntity.notFound().build();
        }
        update.setId(id);
        products.update(id, update);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        if (products.findById(id) != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
