package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductServiceImpl;
import java.util.List;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

  private final ProductServiceImpl productService;

  public ProductApiController(ProductServiceImpl productService) {
    this.productService = productService;
  }

  // 상품 조회
  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProduct(
      @PathVariable Long productId
  ) {
    ProductResponse result = productService.getProductById(productId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(result);
  }

  // 상품 목록 조회
  @GetMapping("/all")
  public ResponseEntity<List<ProductResponse>> getProductList(){
    return ResponseEntity.status(HttpStatus.OK)
        .body(productService.getProductList());
  }

  // 상품 생성
  // return body는 없이 return하는 게 좋을까?
  // 아니면 생성된 id 정도만 return하는 게 좋을까? -> 이 경우에는 NO_CONTENT일까? 아니면 그냥 OK?
  @PostMapping("")
  public ResponseEntity createProduct(
      @RequestBody ProductRequest request
  ) {
    productService.save(request);

    return ResponseEntity.status(HttpStatus.CREATED)
        .build();
  }

  // 상품 수정
  // API 명세서에는 PathVariable로 productId를 받아오도록 명시되어있지만,
  // 데이터베이스를 연결하지 않은 상태에서는 상품 생성 요청을 보낼 때
  // 요청 dto에 상품 id까지 담겨서 오는 것을 가정하겠습니다.
  @PatchMapping("/{productId}")
  public ResponseEntity updateProduct(
      @RequestBody ProductRequest request,
      @PathVariable Long productId
  ) {
    productService.update(request);

    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .build();
  }


  // 상품 삭제
  @DeleteMapping("/{productId}")
  public ResponseEntity deleteProduct(
      @PathVariable Long productId
  ) {
    productService.deleteById(productId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .build();
  }

}

