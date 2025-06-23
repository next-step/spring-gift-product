package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {

        if (requestDto != null) {

            String name = requestDto.getName();
            Integer price = requestDto.getPrice();
            String imageUrl = requestDto.getImageUrl();
            return ResponseEntity.ok(productService.createProduct(name, price, imageUrl));
        }
        return ResponseEntity.badRequest().build();
    }
}
