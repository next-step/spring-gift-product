package gift.controller;

import gift.dto.Product;
import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/api/products")
    public ResponseDto addProduct(@RequestBody RequestDto requestDto) {
        long productId = products.isEmpty() ? 1: Collections.max(products.keySet()) + 1;

        Product product = new Product(productId, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());

        products.put(productId, product);

        return new ResponseDto(product);
    }
}
