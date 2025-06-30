package gift;

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
        return productdao.findAll();
    }

    @PostMapping("/product/add")
    public ProductDto saveProduct(@RequestBody ProductDto productdto) {
        ProductDto productdto1;
        productdto1 = new ProductDto(productdto.getName(), productdto.getPrice(), productdto.getImageUrl());
        productdao.save(productdto1);
        return productdto1;
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable String id) {
        return productdao.findById(id);
    }

    @PatchMapping("/product/{id}/update")
    public ProductDto updateProduct(@PathVariable String id, @RequestBody ProductDto updateProductdto) {
        ProductDto productdto1;
        productdto1 = new ProductDto(updateProductdto.getName(), updateProductdto.getPrice(), updateProductdto.getImageUrl());
        Product product = productdao.findById(id);
        productdao.update(id, productdto1);
        return productdto1;
    }

    @DeleteMapping("/product/{id}/delete")
    public Product deleteById(@PathVariable String id) {
        Product product = productdao.findById(id);
        productdao.delete(id);
        return product;
    }
}
