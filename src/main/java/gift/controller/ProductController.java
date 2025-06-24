package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        List<ProductResponseDto> productList = productService.findAllProducts();

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable Long id) {

        ProductResponseDto dto = productService.findProductById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> craeteProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        return new ResponseEntity<>(productService.saveProduct(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @Valid @RequestBody ProductRequestDto dto,
            @PathVariable Long id) {

        return new ResponseEntity<>(productService.updateProduct(id, dto), HttpStatus.OK);

    }

}
