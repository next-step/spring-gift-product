package gift;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


record Product(long id, String name, int price, String imageURL){

}

@RestController
class ProductController {
    //임시 저장소
    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1L; //auto_increment용

    //상품 조회
    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam(value = "id", defaultValue = "") long id) {
        return products.containsKey(id) ?
                new ResponseEntity<>(products.get(id), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //상품 추가
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        products.put(currentId, product);
        currentId++;
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //상품 수정
    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        if (!products.containsKey(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        products.put(id, product);
        return new  ResponseEntity<>(product, HttpStatus.OK);
    }

}