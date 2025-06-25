package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    // 의존성 고정하여 안전하게 유지
    private final ProductService productService;
    
    // 의존성 주입
    private ProductController(ProductService productService) {this.productService = productService;}

    /*
     * 제품 하나 조회
     * @param id 식별자
     * @return : ProductResponseDto JSON
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    /*
     * 제품 모두 조회
     * @return : List<ProductResponseDto> JSON
     */
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> findAllProducts(){
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    /*
     * 제품 추가
     * @param : ProductRequestDto JSON
     * @return : ProductResponseDto JSON
     */
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto requestDto){
        return new ResponseEntity<>(productService.saveProduct(requestDto), HttpStatus.CREATED);
    }

    /*
     * 제품 수정
     * @param id 식별자
     * @param ProductRequestDto JSON
     * @return : ProductResponseDto JSON
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto requestDto){
        return new ResponseEntity<>(productService.updateProduct(id, requestDto.name(), requestDto.price()), HttpStatus.OK);
    }

    /*
     * 제품 삭제
     * @param id 식별자
     * @return Void 상태코드
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        
        // 성공한 경우에만 OK 반환
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
