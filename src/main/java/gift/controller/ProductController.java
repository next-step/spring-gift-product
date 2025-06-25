package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> result = service.getAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        Product product = service.getById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProductResponseDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto req) {
        Product saved = service.createProduct(req);
        return ResponseEntity.status(201).body(new ProductResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id,
            @RequestBody ProductRequestDto req) {
        Product updated = service.updateProduct(id, req);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProductResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build(); // 없는 경우 404
        }
        return ResponseEntity.noContent().build(); // 삭제 성공 시 204 No Content
    }
}