package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {

    }
    @PostMapping("/product")
    public String createProduct(@RequestBody Product product) {
        products.put(product.getId(), product);
        return "상품 생성: " + product.getId();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        return product;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        Collection<Product> productList = products.values();
        return productList;
    }

    @PatchMapping("/product/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        Product oldProduct = products.get(id);

        if(updateProduct.getName() != null) {
            oldProduct.setName(updateProduct.getName());
        }

        if(updateProduct.getPrice() != 0) {
            oldProduct.setPrice(updateProduct.getPrice());
        }

        if(updateProduct.getImageUrl() != null) {
            oldProduct.setImageUrl(updateProduct.getImageUrl());
        }

        return oldProduct;
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        products.remove(id);
        return "상품 삭제: " + id;
    }
}
