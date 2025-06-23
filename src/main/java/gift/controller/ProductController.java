package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // 임시 DB
    private final Map<Long, Product> products = new HashMap<>();

    // id값
    private long nextId = 1;

    // 상품 생성
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductRequestDto productRequestDto) {
        long productId = nextId++;
        Product product = new Product(productId, productRequestDto.getName(),
                productRequestDto.getPrice(), productRequestDto.getImageUrl());
        products.put(productId, product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ProductResponseDto(product));
    }

}