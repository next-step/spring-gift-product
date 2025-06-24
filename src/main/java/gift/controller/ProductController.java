package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 생성
    @PostMapping // 요청
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductRequestDto requestDto) {
        return new ResponseEntity<>(productService.saveProduct(requestDto), HttpStatus.CREATED);
    }

    // 목록 조회
    @GetMapping
    public List<ProductResponseDto> findAllProducts() {
        return productService.findAllProducts();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }




}
