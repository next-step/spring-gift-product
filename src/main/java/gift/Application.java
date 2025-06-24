package gift;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
@Component
class ProductRepository {
    final Map<Long, Product> products = new HashMap<>();

    public Product get(long id) {
        return products.get(id);
    }

    public boolean containsKey(long id) {
        return products.containsKey(id);
    }

    public void add(Product product) {
        products.put(product.id(), product);
    }

    public void delete(long id) {
        products.remove(id);
    }

    public void update(long id, Product product) {
        products.put(id, product);
    }

    public Collection<Product> findAll() {
        return products.values();
    }
}

record Product(long id, String name, int price, String imageURL){

}

@Controller
class ProductController {
    //임시 저장소
    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    //상품 조회
    @GetMapping("/api/products")
    public String getProduct(@RequestParam(value = "id", defaultValue = "0") long id, Model model) {

        model.addAttribute("allProducts",products.findAll());
        return "products";
    }

    //상품 추가
    @PostMapping("/api/products")
    public String createProduct(@RequestParam long id, @RequestParam String name, @RequestParam int price, @RequestParam String imageURL) {
        Product product = new Product(id, name, price, imageURL);
        products.add(product);
        return "redirect:/api/products";
    }

    //상품 수정
    @PatchMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        if (!products.containsKey(id)) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        products.update(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //상품 삭제
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        if (!products.containsKey(id)) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        products.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}