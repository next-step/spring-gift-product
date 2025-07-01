package gift.controller;

import gift.model.Product;
import gift.repository.ProductDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.getProductById(id);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        productDao.insertProduct(product);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productDao.removeProduct(id);
    }

    @PatchMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productDao.updateProduct(id, productDao.getProductById(id), product);
    }
}