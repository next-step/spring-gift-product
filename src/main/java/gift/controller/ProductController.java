package gift;

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

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 생성
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        try {
            Product product = productService.createProduct(request);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // 상품 조회
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        try {
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
        @PathVariable Long productId,
        @RequestBody ProductRequest request) {
        try {
            Product updatedProduct = productService.updateProduct(productId, request);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 모든 상품 목록 조회
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}