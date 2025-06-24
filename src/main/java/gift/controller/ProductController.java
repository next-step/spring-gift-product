package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 전체 조회
    @GetMapping
    public List<ProductResponseDto> productList() {
        return productService.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> productById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(new ProductResponseDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 상품 생성
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.create(requestDto);
        ProductResponseDto responseDto = new ProductResponseDto(product);

        // URI 문자열 구성
        // URI 문자열을 구성하는 이유? REST 원칙에 따라 새로운 리소스 생성 경우
        // "응답에 그 리소스 위치(URI)를 알려줘야 한다" 를 지키기 위해
        URI location = URI.create("/api/products/" + product.getId());

        return ResponseEntity.created(location).body(responseDto);
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.update(id, productRequestDto).map(product -> ResponseEntity.ok(new ProductResponseDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

}
