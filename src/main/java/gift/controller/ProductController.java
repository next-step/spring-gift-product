package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import java.util.List;
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
@RequestMapping("/api/products")//코드리뷰: 코드중복줄이기
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping("")
  public ResponseEntity<List<ProductResponseDto>> findAllProduct() {
    return new ResponseEntity<>(service.findAllProduct(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findProductById(id), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto requestDto) {
    //코드리뷰: 200대신 더 적절한 201(created) 사용하기
    return new ResponseEntity<>(service.createProduct(requestDto), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
      @RequestBody ProductRequestDto requestDto) {
    return new ResponseEntity<>(service.updateProduct(id, requestDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
    service.deleteProduct(id);
    //코드리뷰: 200대신 더 적절한 204(NO_CONTENT) 사용하기
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
