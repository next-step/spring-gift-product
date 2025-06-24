package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @PostMapping
  public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
    ProductResponse response = productService.addProduct(request);
    return ResponseEntity.status(201).body(response);
  }

}