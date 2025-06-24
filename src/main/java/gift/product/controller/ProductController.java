package gift.product.controller;

import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductCreateRequestDto product) {
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id,
                                              @Valid @RequestBody ProductUpdateRequestDto product) {
        productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
