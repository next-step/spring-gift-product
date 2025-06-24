package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. 상품 생성
    @PostMapping
    public ResponseEntity<ProductResponseDto>  createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.CREATED);
    }

    // 2. 모든 상품 조회
    @GetMapping
    public List<ProductResponseDto> searchAllProducts() {
        return productService.searchAllProducts();
    }

    // 3. 특정 상품 조회 (ID로)
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> searchProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.searchProductById(id), HttpStatus.OK);
    }

    // 4. 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        // ID를 서비스에 전달하고 업데이트
        return new ResponseEntity<>(productService.updateProduct(id, productRequestDto), HttpStatus.OK);
    }

    // 5. 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
