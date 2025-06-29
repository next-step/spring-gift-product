package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import gift.service.ProductService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
        @RequestBody ProductRequestDto requestDto) {
        return new ResponseEntity<>(productService.create(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.find(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
        @PathVariable Long productId,
        @RequestBody ProductRequestDto requestDto
    ) {
        return new ResponseEntity<>(productService.update(productId, requestDto),
            HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {

        productService.delete(productId);

        // 삭제에 성공한다면, 204 no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
