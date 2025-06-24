package gift.controller;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDto> productList() {
        return productService.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> productById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(new ProductResponseDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

}
