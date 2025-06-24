package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // 상품 생성
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        if (request.name() == null || request.price() == null || request.imageUrl() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // id 를 지정하지 않은 경우 id 자동 생성
        // 지정된 id가 이미 존재하는 경우 Conflict
        Long id = request.id() != null ? request.id() : idGenerator.getAndIncrement();
        if (products.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // 상품 조회
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = products.get(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
        @PathVariable Long productId,
        @RequestBody ProductRequest request) {
        Product existingProduct = products.get(productId);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 요청된 필드만 수정, 나머지 필드는 기존 값 유지
        String updatedName = request.name() != null ? request.name() : existingProduct.name();
        int updatedPrice = request.price() != null ? request.price() : existingProduct.price();
        String updatedImageUrl =
            request.imageUrl() != null ? request.imageUrl() : existingProduct.imageUrl();

        Product updatedProduct = new Product(productId, updatedName, updatedPrice, updatedImageUrl);
        products.put(productId, updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (!products.containsKey(productId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        products.remove(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 모든 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = new ArrayList<>(products.values());
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    
}