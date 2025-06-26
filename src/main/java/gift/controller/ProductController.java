package gift.controller;


import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")//
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProduct(id),HttpStatus.OK);
    }


    @PostMapping//ResponseEntity는 spring framework에서 제공하는 HTTP 응답 객체
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){//감싸기
        return new ResponseEntity<>(productService.addProduct(productRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productService.updateProduct(id,productRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new 	ResponseEntity<>(HttpStatus.OK);
    }


}

