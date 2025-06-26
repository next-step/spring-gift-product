package gift.controller;

import gift.dto.CustomPage;
import gift.dto.ProductCreateRequest;
import gift.dto.ProductUpdateRequest;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<CustomPage<Product>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("페이지 번호와 크기는 양수여야 합니다.");
        }
        return new ResponseEntity<>(productService.getBy(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("상품 ID는 필수입니다.");
        }
        Product product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateRequest dto) {
        Product product = productService.create(dto.toProduct());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest dto) {
        if (id == null) {
            throw new IllegalArgumentException("상품 ID는 필수입니다.");
        }
        Product updatedProduct = productService.update(dto.toEntity(id));
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("상품 ID는 필수입니다.");
        }
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
