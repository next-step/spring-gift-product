package gift.controller;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.CreateProductResponse;
import gift.dto.UpdateProductRequest;
import gift.dto.UpdateProductResponse;
import gift.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
//@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

//    @PostMapping
    public HttpEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        CreateProductResponse createProductResponse = service.save(request);

        return new ResponseEntity<>(createProductResponse, HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
    public HttpEntity<Product> findProductById(@PathVariable Long id) {
        Product findProduct = service.findById(id);

        return new ResponseEntity<>(findProduct, HttpStatus.OK);
    }

//    @GetMapping
    public HttpEntity<List<Product>> findProducts() {
        List<Product> products = service.findAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
    public HttpEntity<UpdateProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        UpdateProductResponse updateProductResponse = service.update(id, request);

        return new ResponseEntity<>(updateProductResponse, HttpStatus.OK);

    }

//    @DeleteMapping("/{id}")
    public HttpEntity<Void> deleteProduct(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
