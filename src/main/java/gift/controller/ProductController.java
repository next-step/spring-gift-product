package gift.controller;

import gift.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {
        products.put(1L, new Product(1L, "아이스 아메리카노", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // 현재 등록된 상품 중 가장 큰 ID를 가져와서 +1
        Long newId = products.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
        product.setId(newId);
        products.put(newId, product);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(product); // 저장된 상품 객체 반환
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existing = products.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build(); // 404
        }

        // 기존 객체를 덮어쓰기
        updatedProduct.setId(id);
        products.put(id, updatedProduct);

        return ResponseEntity.ok(updatedProduct); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build(); // 404
        }

        products.remove(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}
