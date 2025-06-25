package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findProduct(
            @PathVariable Long productId) {
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(
            @RequestBody ProductRequestDto dto
    ) {
        return new ResponseEntity<ProductResponseDto>(productService.saveProduct(dto), HttpStatus.OK);
    }
}

