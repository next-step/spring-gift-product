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

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> ProductResponseDtoList = productService.findAllProducts();
        return new ResponseEntity<>(ProductResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto) {
        return new ResponseEntity<>(productService.saveProduct(dto), HttpStatus.CREATED);
    }

}
