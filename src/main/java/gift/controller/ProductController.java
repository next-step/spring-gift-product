package gift.controller;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductAddResponseDto;
import gift.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ProductAddResponseDto> addProduct(
            @RequestBody ProductAddRequestDto requestDto
    ) {
        ProductAddResponseDto responseDto = productServiceImpl.addProduct(requestDto.name(), requestDto.price(), requestDto.url());
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

}
