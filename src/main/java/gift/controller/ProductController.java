package gift.controller;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping
    public ResponseEntity<ResponseDto> addProduct(@RequestBody RequestDto requestDto) {
        long productId = products.isEmpty() ? 1: Collections.max(products.keySet()) + 1;

        Product product = new Product(productId, requestDto.name(), requestDto.price(), requestDto.imageUrl());

        if (requestDto.name() == null || requestDto.price() == null || requestDto.imageUrl() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        products.put(productId, product);

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.CREATED);
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

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto> getProductByproductId(@PathVariable Long productId) {

        Product product = products.get(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable Long productId,
                                                     @RequestBody RequestDto requestDto) {

        Product product = products.get(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (requestDto.name() == null || requestDto.price() == null || requestDto.imageUrl() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        product.update(requestDto);

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long productId) {
        Product product = products.get(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        products.remove(productId);

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.OK);
    }
}