package gift.product.controller;

import gift.global.exception.BadProductRequestException;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductResponse;
import gift.product.dto.ProductUpdateRequest;
import gift.product.service.ProductService;
import gift.util.LocationGenerator;
import gift.util.StringValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductCreateRequest dto) {

        if (!StringValidator.isNotBlank(dto.getName()) ||
                !StringValidator.isNotBlank(dto.getImageURL()) || dto.getPrice() <= 0)
            throw new BadProductRequestException("이름, 가격, 이미지 주소는 필수입니다.");


        UUID savedId = productService.addProduct(dto);


        return ResponseEntity.status(HttpStatus.CREATED).location(
                LocationGenerator.generate(savedId)
        ).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> response = productService.findAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {

        ProductResponse response = productService.findProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {

        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateRequest dto) {

        productService.updateProduct(id, dto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
