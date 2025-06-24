package gift.product.controller;

import gift.product.domain.Product;
import gift.product.dto.ProductRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
     * 상품 등록 API
     *
     * @param requestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody @Valid ProductRequestDto requestDto){
        Product savedProduct = productService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 전체 상품 조회 API
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> responses = productService.findAll()
                .stream()
                .map(product -> ProductResponseDto.productFrom(product))
                .toList();

        return ResponseEntity.ok(responses);
    }

    /**
     * 특정 상품 조회 API
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        return ResponseEntity.ok(ProductResponseDto.productFrom(product));
    }

    /**
     * 상품 업데이트 API
     *
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDto requestDto){
        productService.update(id, requestDto);
        return ResponseEntity.noContent().build(); // 204
    }

    /**
     * 상품 삭제 API
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
