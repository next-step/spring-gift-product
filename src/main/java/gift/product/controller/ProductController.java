package gift.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gift.product.dto.ProductRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.getProduct(id);
        return  ResponseEntity.ok(productResponseDto);
    }

    //전체 조회
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> productResponseDtos = productService.getAllProducts();
        return  ResponseEntity.ok(productResponseDtos);
    }

    //특정 상품 추가
    @PostMapping()
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
        return  ResponseEntity.ok(productResponseDto);
    }

    //특정 상품 수정
    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequestDto,
                                                            @PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.updateProduct(id,productRequestDto);
        return  ResponseEntity.ok(productResponseDto);
    }

    //삭제
    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.deleteProduct(productRequestDto.getId());
        return ResponseEntity.noContent().build();
    }

}
