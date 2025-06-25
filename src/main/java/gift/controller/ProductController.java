package gift.controller;

import gift.domain.Product;
import gift.dto.request.ProductRequest;
import gift.dto.response.ProductResponse;
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
    private final AtomicLong idgen = new AtomicLong(1);

    @PostMapping // 상품 등록
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest request) {
        Long id = idgen.getAndIncrement();
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return ResponseEntity
                .created(null)
                .body(new ProductResponse(product));
    }

    @GetMapping("/{productId}") // 상품 단건 조회
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        Product product = products.get(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping // 상품 목록 전체 조회
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
    }

    @PutMapping("/{productId}") // 상품 수정 - 부분수정 가능
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest request) {
        Product exist = products.get(productId);
        if (exist == null) {
            return ResponseEntity.notFound().build();
        }

        if (request.name() != null && !request.name().isBlank()) {
            exist.setName(request.name());
        }
        if (request.price() > 0) {
            exist.setPrice(request.price());
        }
        if (request.imageUrl() != null && !request.imageUrl().isBlank()) {
            exist.setImageUrl(request.imageUrl());
        }

        return ResponseEntity.ok(new ProductResponse(exist));
    }

    @DeleteMapping("/{productId}") //상품 삭제
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        Product exist = products.get(productId);
        if (exist == null) {
            return ResponseEntity.notFound().build();
        }
        products.remove(productId);
        return ResponseEntity.noContent().build();
    }
}