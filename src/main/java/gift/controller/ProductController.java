package gift.controller;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gift.service.ProductService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. 상품 추가
    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@RequestBody RequestDto dto) {
        ResponseDto response = productService.create(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProduct(@PathVariable Long id) {
        ResponseDto response = productService.findById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 3. 상품 수정

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody RequestDto dto
    ) {
        ResponseDto response = productService.update(id, dto);

        return new ResponseEntity<>(response, HttpStatus.OK);
     }

    // 4. 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}