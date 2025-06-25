package gift.controller;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.common.Page;
import gift.service.ProductManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductManagementService productService;

    public ProductController(ProductManagementService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductRequest request) {
        productService.create(request);
    }

    @GetMapping
    public Page<Product> getAllByPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize

    ) {
        return productService.getAllByPage(pageNumber, pageSize);
    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable Long productId) {
        return productService.getById(productId);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long productId, @RequestBody ProductRequest request) {
        productService.update(productId, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long productId) {
        productService.deleteById(productId);
    }
}
