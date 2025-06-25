package gift.controller;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.PatchProductRequestDto;
import gift.dto.UpdateProductRequestDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        List<ProductResponseDto> list = productService.findAllProducts();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 상품 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.findProductById(id);

        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    // 상품 추가
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody CreateProductRequestDto requestDto) {
        ProductResponseDto productResponseDto = productService.createProduct(
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 상품 일부 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchProduct(
            @PathVariable Long id,
            @RequestBody PatchProductRequestDto requestDto) {

        if (requestDto.getName() == null && requestDto.getPrice() == null && requestDto.getImageUrl() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 값이 존재하지 않습니다.");
        }

        productService.patchProduct(
                id,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 상품 전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequestDto requestDto
    ) {

        productService.updateProduct(
                id,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
