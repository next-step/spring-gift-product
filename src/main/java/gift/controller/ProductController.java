package gift.controller;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // 1. 상품 추가
    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@RequestBody RequestDto dto) {
        Product product = new Product(productId, dto.getName());

        products.put(productId, product);

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.CREATED);
    }

    // 2. 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProduct(@PathVariable Long id) {
        Product product = products.get(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.OK);
    }

    // 3. 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody RequestDto dto
    ) {
        Product product = products.get(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dto.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        product.update(dto);

        return new ResponseEntity<>(new ResponseDto(product), HttpStatus.OK);
    }

    // 4. 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}