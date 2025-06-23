package gift;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

record Product(long id, String name, int price, String imageURL){

}

@Component
class ProductRepository {
    final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1L; //auto_increment용

    public Product get(long id) {
        return products.get(id);
    }

    public boolean containsKey(long id) {
        return products.containsKey(id);
    }

    public void add(Product product) {
        products.put(currentId++, product);
    }

    public void delete(long id) {
        products.remove(id);
    }

    public void update(long id, Product product) {
        products.put(id, product);
    }
}

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


@RestController
class ProductController {
    //임시 저장소
    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    //상품 조회
    @GetMapping("/api/products")
    public ResponseEntity<Product> getProduct(@RequestParam(value = "id", defaultValue = "") long id) {
        return products.containsKey(id) ?
                new ResponseEntity<>(products.get(id), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //상품 추가
    @PostMapping("/api/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //상품 수정
    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        if (!products.containsKey(id)) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        products.update(id, product);
        return new  ResponseEntity<>(product, HttpStatus.OK);
    }

    //상품 삭제
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        if (!products.containsKey(id)) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        products.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}