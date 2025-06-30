package gift.Controller;

import gift.Entity.Product;
import gift.dao.ProductDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductDBController {

    private final ProductDao productDao;

    public ProductDBController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        productDao.insertProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> showAllProducts() {
        return ResponseEntity.ok(productDao.showProducts());
    }

    @GetMapping("/products/{id}")
    public Product selectProduct(@PathVariable Long id) {
        return productDao.selectProduct(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productDao.updateProduct(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
