package gift.controller;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

  private final Map<Long, Product> productsMap = new HashMap<>();

  @GetMapping("/products")
  public ResponseEntity<List<Product>> findAllProduct() {
    return new ResponseEntity<>(new ArrayList<>(productsMap.values()), HttpStatus.OK);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable Long id) {
    Product findByIdProduct = productsMap.get(id);
    return new ResponseEntity<>(findByIdProduct, HttpStatus.OK);
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    productsMap.put(product.getId(), product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PatchMapping("/products/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id,
      @RequestBody Product newProduct) {
    productsMap.replace(id, newProduct);
    return new ResponseEntity<>(newProduct, HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
    productsMap.remove(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
