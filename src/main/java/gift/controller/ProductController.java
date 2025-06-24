package gift.controller;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {
    
    private final ProductService productService;
    
    //생성자 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    //상품 추가 api
    @PostMapping
    public ResponseEntity<AddProductResponseDto> addProduct(
        @RequestBody AddProductRequestDto requestDto
    ) {
        AddProductResponseDto responseDto = productService.addProduct(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
