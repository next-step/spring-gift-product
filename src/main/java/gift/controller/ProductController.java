package gift.controller;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.common.Page;
import gift.service.ProductManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductManagementService productService;

    public ProductController(ProductManagementService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
        Product createdProduct = productService.create(request);
        URI location = URI.create("/api/products/" + createdProduct.id());

        return ResponseEntity.created(location)
                .body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllByPage(
            @RequestParam(defaultValue = "1") @Min(1) Integer pageNumber,
            @RequestParam(defaultValue = "10") @Min(1) Integer pageSize) {

        Page<Product> page = productService.getAllByPage(pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable Long productId) {
        Product product = productService.getById(productId);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> update(@PathVariable Long productId,
            @RequestBody ProductRequest request) {
        productService.update(productId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllByIds(
            @RequestBody(required = false) List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            productService.deleteAll();
        } else {
            productService.deleteAllByIds(ids);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
