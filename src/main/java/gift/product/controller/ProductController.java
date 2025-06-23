package gift.product.controller;

import gift.product.dto.ProductCreateRequest;
import gift.product.service.ProductService;
import gift.util.LocationGenerator;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductCreateRequest dto) {
        String savedId = productService.addProduct(dto);


        return ResponseEntity.status(HttpStatus.CREATED).location(
                LocationGenerator.generate(savedId)
        ).build();
    }
}
