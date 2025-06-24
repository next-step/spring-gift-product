package gift.Controller;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  private final Map<Long, Product> products = new HashMap<>();
  private Long id = 0L;

  // 전체 상품 조회
  @GetMapping
  public List<Product> getAllProducts() {
    return new ArrayList<>(products.values());
  }

  // 단건 상품 조회
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    Product product = products.get(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  // 상품 추가
  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    product.setId(id);
    products.put(id, product);
    id++;
    return ResponseEntity.ok(product);
  }

  // 상품 수정
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id,
      @RequestBody Product updateProduct) {
    Product existing = products.get(id);
    if (existing == null) {
      return ResponseEntity.notFound().build();
    }
    updateProduct.setId(id);
    products.put(id, updateProduct);
    return ResponseEntity.ok(updateProduct);
  }

  // 상품 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (!products.containsKey(id)) {
      return ResponseEntity.notFound().build();
    }
    products.remove(id);
    return ResponseEntity.noContent().build();
  }
}
