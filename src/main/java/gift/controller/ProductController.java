package gift.controller;


import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @GetMapping("/api/products")
    public List<ResponseDto> getProducts() {

        List<ResponseDto> responseList = new ArrayList<>();

        for (Product product : products.values()) {
            ResponseDto responseDto = new ResponseDto(product);
            responseList.add(responseDto);
        }

        return responseList;
    }
}
