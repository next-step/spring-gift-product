package gift.controller;

import gift.common.code.CustomResponseCode;
import gift.common.dto.CustomResponseBody;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<CustomResponseBody<ProductResponse>> createProduct(
        @Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.create(request);

        return ResponseEntity
            .status(CustomResponseCode.CREATED.getHttpStatus())
            .body(CustomResponseBody.of(CustomResponseCode.CREATED, response));
    }

    @GetMapping
    public ResponseEntity<CustomResponseBody<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> responses = productService.getAllProducts();

        return ResponseEntity
            .status(CustomResponseCode.LIST_RETRIEVED.getHttpStatus())
            .body(CustomResponseBody.of(CustomResponseCode.LIST_RETRIEVED, responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponseBody<ProductResponse>> getProduct(@PathVariable Long id) {
        ProductResponse response = productService.getProduct(id);

        return ResponseEntity
            .status(CustomResponseCode.RETRIEVED.getHttpStatus())
            .body(CustomResponseBody.of(CustomResponseCode.RETRIEVED, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponseBody<ProductResponse>> updateProduct(
        @PathVariable Long id,
        @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response = productService.update(id, request);

        return ResponseEntity
            .status(CustomResponseCode.UPDATED.getHttpStatus())
            .body(CustomResponseBody.of(CustomResponseCode.UPDATED, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Void>> deleteProduct(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity
            .status(CustomResponseCode.DELETED.getHttpStatus())
            .body(CustomResponseBody.of(CustomResponseCode.DELETED));
    }
}
