package gift.controller;

import gift.model.Product;
import org.springframework.web.bind.annotation.*;
import gift.service.ProductService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.removeProduct(id);
    }
    @PatchMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }
}
