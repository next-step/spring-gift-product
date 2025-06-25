package gift.controller;

import gift.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public Collection<Product> getProducts() {
        return products.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        return (product != null) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        long id = idGenerator.incrementAndGet();
        product.setId(id);
        products.put(id, product);
        return product;
    }

}