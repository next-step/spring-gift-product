package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto productRequestDto) {
    ProductResponseDto createdProduct = productService.createProduct(productRequestDto);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdProduct.id())
        .toUri();

    return ResponseEntity
        .created(location)
        .body(createdProduct);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> searchAllProducts() {
    return ResponseEntity.ok(productService.searchAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDto> searchProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.searchProductById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
      @RequestBody ProductRequestDto productRequestDto) {
    return ResponseEntity.ok(productService.updateProduct(id, productRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
