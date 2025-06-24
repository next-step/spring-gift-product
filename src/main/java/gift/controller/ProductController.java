package gift.controller;

import gift.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @GetMapping
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        long id = nextId++;
        Product newProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        products.put(id, newProduct);

        // 201 Created + Location 헤더 설정
        return ResponseEntity.created(URI.create("/api/products/" + id)).build();
    }
}
