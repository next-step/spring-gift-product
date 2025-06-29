package gift.product.controller;

import gift.common.annotation.PageParam;
import gift.common.dto.PageRequest;
import gift.common.dto.PagedResult;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.GetProductResDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  @GetMapping("/{productId}")
  public ResponseEntity<GetProductResDto> getProductById(@PathVariable Long productId) {
    GetProductResDto responseDto = productService.getProductById(productId);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<PagedResult<GetProductResDto>> getAllProducts(
      @PageParam PageRequest pageRequest
  ) {
    PagedResult<GetProductResDto> pagedResult = productService.getAllByPage(pageRequest);
    return ResponseEntity.ok(pagedResult);
  }

  @PostMapping
  public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductReqDto dto) {
    Long id = productService.createProduct(dto);
    URI uri = URI.create("/api/products/" + id);
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateProduct(@PathVariable Long id,
      @Valid @RequestBody UpdateProductReqDto dto) {
    productService.updateProduct(id, dto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

}
