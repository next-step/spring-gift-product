package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
<<<<<<< step2
import gift.service.ProductService;
=======
import gift.service.ProductServiceImpl;
>>>>>>> seongwon02
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< step2
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
=======
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
>>>>>>> seongwon02
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        List<ProductResponseDto> productList = productService.findAllProducts();

<<<<<<< step2
        return ResponseEntity.ok(productList);
=======
        return new ResponseEntity<>(productList, HttpStatus.OK);
>>>>>>> seongwon02
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable Long id) {

        ProductResponseDto dto = productService.findProductById(id);

<<<<<<< step2
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).
                body(productService.saveProduct(dto));
=======
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> craeteProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        return new ResponseEntity<>(productService.saveProduct(dto), HttpStatus.CREATED);
>>>>>>> seongwon02
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @Valid @RequestBody ProductRequestDto dto,
            @PathVariable Long id) {

<<<<<<< step2
        return ResponseEntity.ok(productService.updateProduct(id, dto));
=======
        return new ResponseEntity<>(productService.updateProduct(id, dto), HttpStatus.OK);
>>>>>>> seongwon02
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

<<<<<<< step2
        return ResponseEntity.noContent().build();
=======
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
>>>>>>> seongwon02
    }

}
