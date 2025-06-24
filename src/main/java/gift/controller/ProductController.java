package gift.controller;


import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping
    public ResponseDto addProduct(@RequestBody RequestDto requestDto) {
        long productId = products.isEmpty() ? 1: Collections.max(products.keySet()) + 1;

        Product product = new Product(productId, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());

        products.put(productId, product);

        return new ResponseDto(product);
    }

    @GetMapping
    public List<ResponseDto> getProducts() {

        List<ResponseDto> responseList = new ArrayList<>();

        for (Product product : products.values()) {
            ResponseDto responseDto = new ResponseDto(product);
            responseList.add(responseDto);
        }

        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseDto getProductById(@PathVariable Long id) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        return new ResponseDto(product);
    }

    @PatchMapping("/{id}")
    public ResponseDto updateProduct(
            @PathVariable Long id,
            @RequestBody RequestDto requestDto
    ) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        product.update(requestDto);

        return new ResponseDto(product);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteProduct(@PathVariable Long id) {
        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        products.remove(id);

        return new ResponseDto(product);
    }
}
