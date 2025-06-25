package gift.controller;

import gift.domain.Product;
import gift.dto.request.ProductRequest;
import gift.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idgen = new AtomicLong(1);

    @PostMapping // 상품 등록
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest request) {
        Long id = idgen.getAndIncrement();
        Product product = new Product(id, request.getName(), request.getPrice(), request.getImageUrl());
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

        if (request.getName() != null && !request.getName().isBlank()) {
            exist.setName(request.getName());
        }
        if (request.getPrice() > 0) {
            exist.setPrice(request.getPrice());
        }
        if (request.getImageUrl() != null && !request.getImageUrl().isBlank()) {
            exist.setImageUrl(request.getImageUrl());
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