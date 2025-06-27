package gift.controller;

import gift.dto.request.ProductCreateRequestDto;
import gift.dto.request.ProductUpdateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductGetResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
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

    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(
        @Valid @RequestBody ProductCreateRequestDto productCreateRequestDto) {

        return new ResponseEntity<>(productService.saveProduct(productCreateRequestDto),
            HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductGetResponseDto> getProducts() {

        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetResponseDto> getProductById(
        @PathVariable Long productId) {

        return new ResponseEntity<>(productService.findProductById(productId),
            HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProductById(@PathVariable Long productId,
        @Valid @RequestBody ProductUpdateRequestDto productUpdaterequestDto) {

        productService.updateProductById(productId, productUpdaterequestDto.name(),
            productUpdaterequestDto.price(),
            productUpdaterequestDto.imageUrl());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {

        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}