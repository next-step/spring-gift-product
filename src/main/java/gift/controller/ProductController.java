package gift.controller;

import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {

  private final ProductRepository products;

  public ProductController(ProductRepository productRepository) {
    this.products = productRepository;
  }

  //상품 조회
  @GetMapping("/api/products/{productId}")
  public ResponseEntity<Product> getProduct(@PathVariable long productId) {
    return products.containsKey(productId) ?
        new ResponseEntity<>(products.get(productId), HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  //상품 추가
  @PostMapping("/api/products")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    products.add(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  //상품 수정
  @PatchMapping("/api/products/{productId}")
  public ResponseEntity<Product> updateProduct(@PathVariable long productId,
      @RequestBody Product product) {
    if (!products.containsKey(productId)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    products.add(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  //상품 삭제
  @DeleteMapping("/api/products/{productId}")
  public ResponseEntity<Product> deleteProduct(@PathVariable long productId) {
    if (!products.containsKey(productId)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    products.delete(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}