package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        ProductResponse response = productService.addProduct(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        List<ProductResponse> productResponses = productService.findAllProducts();
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable Long id) {
        ProductResponse productResponse = productService.findProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        List<ProductResponse> productResponses = productService.findAllProducts();
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable Long id) {
        ProductResponse productResponse = productService.findProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {

        ProductResponse updatedProduct = productService.updateProduct(id, request);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}