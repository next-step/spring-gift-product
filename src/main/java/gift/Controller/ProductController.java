package gift.Controller;

import gift.Dto.*;
import gift.Entity.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProducts = new ArrayList<>(products.values());
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.status(404).body("해당 상품을 찾을 수 없습니다: " + id);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductRequest request) {
        String error = validate(request);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Long id = nextId++;
        Product product = new Product(
                id,
                request.getName(),
                request.getPrice(),
                request.getImgUrl()
        );
        products.put(id, product);

        URI location = URI.create("/api/products/" + id);
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        Product existing = products.get(id);
        if (existing == null) {
            return ResponseEntity.status(404).body("해당 상품을 찾을 수 없습니다: " + id);
        }

        String error = validate(request);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        Product updated = new Product(
                id,
                request.getName(),
                request.getPrice(),
                request.getImgUrl()
        );
        products.put(id, updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product removed = products.remove(id);
        if (removed == null) {
            return ResponseEntity.status(404).body("해당 상품을 찾을 수 없습니다: " + id);
        }
        return ResponseEntity.noContent().build();
    }
    private String validate(ProductRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return "상품 이름은 비어 있을 수 없습니다.";
        }
        if (request.getPrice() < 0) {
            return "가격은 0 이상이어야 합니다.";
        }
        if (request.getImgUrl() == null || request.getImgUrl().isBlank()) {
            return "이미지 URL은 비어 있을 수 없습니다.";
        }
        return null;
    }
}
