package gift.controller;


import gift.dto.ProductResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gift.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponseDto> list() { // 응답 DTO 사용
        return service.getAll().stream()
                .map(ProductResponseDto::new) // Product -> ProductResponseDto
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable Long id) {
        return service.getById(id)
                .map(product -> ResponseEntity.ok(new ProductResponseDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }


}

