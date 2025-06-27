package gift.controller;

import gift.dto.response.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
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


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //상품 조회
    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable long productId) {
        return new ResponseEntity<>(productService.getProduct(productId).toResponseDto(),
            HttpStatus.OK);
    }

    //상품 추가
    @PostMapping("/api/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return new ResponseEntity<>(product.toResponseDto(), HttpStatus.CREATED);
    }

    //상품 수정
    @PatchMapping("/api/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable long productId,
        @RequestBody Product product) {
        if (!productService.containsProduct(productId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //상품 삭제
    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long productId) {
        if (!productService.containsProduct(productId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}