package gift.controller;

import gift.dto.ProductPatchDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody ProductRequestDto request
    ) {
        Product created = productService.createProduct(
                request.name(),
                request.price(),
                request.imageUrl()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductResponseDto.from(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id
    ) {
        Product product = productService.getProductById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseDto.from(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getProductList();
        List<ProductResponseDto> response = products.stream()
                .map(product -> ProductResponseDto.from(product))
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductById(
            @PathVariable Long id,
            @Valid @RequestBody ProductPatchDto patch
    ) {
        Product product = productService.updateSelectivelyProductById(
                id,
                patch.getName(),
                patch.getPrice(),
                patch.getImageUrl()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseDto.from(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(
            @PathVariable Long id
    ) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
