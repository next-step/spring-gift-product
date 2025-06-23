package gift.controller;

import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductResponse;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.saveProduct(request);
        ProductResponse response = new ProductResponse(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
