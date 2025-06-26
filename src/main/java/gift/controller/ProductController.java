package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.exception.NotFoundByIdException;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/products")
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Long productId = productService.saveProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDto productRequestDto
    ) {
        productService.updateProduct(productId, productRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundByIdException(NotFoundByIdException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Not Found by ID: " + e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseError(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Invalid Request: " + e.getMessage());
    }
}
