package gift.controller;

import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductResponse;
import gift.dto.product.UpdateProductRequest;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.saveProduct(request);
        ProductResponse response = new ProductResponse(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        productService.updateProduct(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
