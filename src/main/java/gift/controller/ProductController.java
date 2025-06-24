package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto saved = productService.createProduct(requestDto);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
                                                            @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.updateProduct(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getProductList(page, size));
    }
}
