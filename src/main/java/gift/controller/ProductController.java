package gift.controller;

import gift.domain.Product;
import gift.dto.request.ProductRequest;
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
    public ResponseEntity<Product> register(@RequestBody ProductRequest request) {
        Long id = idgen.getAndIncrement();
        Product product = new Product(id, request.getName(), request.getPrice(), request.getImageUrl());
        products.put(id, product);
        return ResponseEntity
                .created(null)
                .body(product);
    }

    @GetMapping("/{productId}") // 상품 단건 조회
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = products.get(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping // 상품 목록 전체 조회
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
    }
}