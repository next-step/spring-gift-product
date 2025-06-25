package gift.controller;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductUpdateRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(
            @RequestBody ProductAddRequestDto requestDto
    ) {
        ProductResponseDto responseDto = productServiceImpl.addProduct(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(
            @PathVariable Long id
    ) {
        ProductResponseDto responseDto = productServiceImpl.findProductById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductById(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequestDto requestDto
    ) {
        ProductResponseDto responseDto = productServiceImpl.updateProductById(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(
            @PathVariable Long id
    ) {
        productServiceImpl.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
