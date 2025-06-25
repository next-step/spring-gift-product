package gift.Controller;

import gift.model.Product;
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

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  // 전체 상품 조회
  @GetMapping
  public List<Product> getAllProducts() {
    return service.findAll();
  }

  // 단건 상품 조회
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // 상품 추가
  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {

    // 상품 생성 후 접근할 수 있는 URI를 Header에 담아 제공
    Product newProduct = service.save(product);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{id}")
        .buildAndExpand(newProduct.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  // 상품 수정
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id
      , @RequestBody Product updateProduct) {
    return service.update(id, updateProduct)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // 상품 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (service.delete(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
