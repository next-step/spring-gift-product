package gift.Controller;

import gift.Dto.*;
import gift.Exception.*;
import gift.Model.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public ProductController() {
        addSampleProduct();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProducts = new ArrayList<>(products.values());
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody ProductRequest request) {
        Long id = nextId++;
        Product product = new Product(
                id,
                request.toName().getValue(),
                request.toPrice().getValue(),
                request.toImgUrl().getValue()
        );
        products.put(id, product);

        URI location = URI.create("/api/products/" + id);
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        Product existing = products.get(id);
        if (existing == null) {
            throw new ProductNotFoundException(id);
        }

        Product updated = new Product(
                id,
                request.toName().getValue(),
                request.toPrice().getValue(),
                request.toImgUrl().getValue()
        );
        products.put(id, updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Product removed = products.remove(id);
        if (removed == null) {
            throw new ProductNotFoundException(id);
        }
        return ResponseEntity.noContent().build();
    }

    private void addSampleProduct() {
        ProductRequest sample = new ProductRequestSample();
        Product product = new Product(
                nextId,
                sample.toName().getValue(),
                sample.toPrice().getValue(),
                sample.toImgUrl().getValue()
        );
        products.put(nextId, product);
        nextId++;
    }
}
