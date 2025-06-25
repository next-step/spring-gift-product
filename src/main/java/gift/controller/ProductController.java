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
        return new ResponseEntity<>(
                ProductResponseDto.from(
                        productService.createProduct(
                                request.name(),
                                request.price(),
                                request.imageUrl()
                        )
                ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                ProductResponseDto.from(
                        productService.getProductById(id)
                ), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getProductList();
        List<ProductResponseDto> response = products.stream()
                .map(product -> ProductResponseDto.from(product))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductById(
            @PathVariable Long id,
            @Valid @RequestBody ProductPatchDto patch
    ) {
        return new ResponseEntity<>(
                ProductResponseDto.from(
                        productService.updateProductById(id, patch.getName(), patch.getPrice(), patch.getImageUrl())
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(
            @PathVariable Long id
    ) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
