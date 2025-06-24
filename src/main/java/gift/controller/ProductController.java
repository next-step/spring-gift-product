package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService = new ProductService();

    @PostMapping
    public ResponseEntity<Map<String, Object>> addProduct(@RequestBody Product product) {

        Map<String, Object> responseBody = new HashMap<>();
        Product savedProduct = productService.addProduct(product);

        responseBody.put("message", "New product created");
        responseBody.put("product", savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
