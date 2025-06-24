package gift.controller;

import gift.dto.request.ProductSaveReqDTO;
import gift.dto.response.ProductResDTO;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ProductResDTO> createProduct(
        @RequestBody ProductSaveReqDTO productSaveReqDTO
    ) {
        return ResponseEntity.ok(productService.save(productSaveReqDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}
