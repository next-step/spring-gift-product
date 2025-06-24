package gift.controller;


import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;//의존성 주입


    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping//ResponseEntity는 spring framework에서 제공하는 HTTP 응답 객체
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){//감싸기
        return new ResponseEntity<>(productService.addProduct(productRequestDto), HttpStatus.OK);
    }
}

