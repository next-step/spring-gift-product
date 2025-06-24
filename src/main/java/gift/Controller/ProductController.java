package gift.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import gift.Model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public ProductController() {
        products.put(nextId, new Product(
                nextId++,
                "아이스 카페 아메리카노 T",
                4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        ));
    }

    @GetMapping
    public List<Product> getAllPRoducts(){
        return new ArrayList<>(products.values());
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.get(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        product.setId(nextId);
        products.put(nextId,product);
        nextId++;
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        products.remove(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated){
        Product product = products.get(id);
        if(product != null){
            product.setName(updated.getName());
            product.setPrice(updated.getPrice());
            product.setImgURL(updated.getImgURL());
        }
        return product;
    }
}