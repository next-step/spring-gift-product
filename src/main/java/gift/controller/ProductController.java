package gift.controller;

import gift.dto.request.ProductSaveReqDTO;
import gift.dto.response.ProductSaveResDTO;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    @PostMapping
    public ResponseEntity<ProductSaveResDTO> createProduct(
        @RequestBody ProductSaveReqDTO productSaveReqDTO
    ) {
        return ResponseEntity.ok(productService.save(productSaveReqDTO));
    }
}
