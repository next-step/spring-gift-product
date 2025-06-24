package gift.controller;


import gift.dto.request.ProductRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }


    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto requestDto) {
        Product saved = productService.createProduct(requestDto);
        return ResponseEntity.status(201).body(new ProductResponseDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> response = products.stream()
                .map(ProductResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }


}
