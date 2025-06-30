package gift;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductDao productdao;

    public ProductController(ProductDao productdao) {
        this.productdao = productdao;
    }

    @GetMapping("/product/list")
    public List<Product> findAll() {
        List<Product> products = productdao.getAll();
        return products;
    }

    @PostMapping("/product/add")
    public ProductDTO saveProduct(@RequestBody ProductDTO productdto) {
        ProductDTO productdto1;
        productdto1 = new ProductDTO(productdto.getName(), productdto.getPrice(), productdto.getImageUrl());
        productdao.save(productdto1);
        return productdto1;
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable Long id) {
        Product product = productdao.findById(id);
        return product;
    }

    @PatchMapping("/product/{id}/update")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO updateProductdto) {
        ProductDTO productdto1;
        productdto1 = new ProductDTO(updateProductdto.getName(), updateProductdto.getPrice(), updateProductdto.getImageUrl());
        Product product = productdao.findById(id);
        if( product != null) {
            productdao.update(id, productdto1);
        }
        return productdto1;
    }

    @DeleteMapping("/product/{id}/delete")
    public Product deleteById(@PathVariable Long id) {
        Product product = productdao.findById(id);
        if( product != null) {
            productdao.delete(id);
        }
        return product;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccess(EmptyResultDataAccessException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
