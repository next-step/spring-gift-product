package gift.domain.product.controller;

import gift.domain.product.model.Product;
import gift.domain.product.dto.AddProductRequest;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody AddProductRequest productRequest) {
        long id = sequence.incrementAndGet();
        Product product = new Product(id, 
        productRequest.getName(), 
        productRequest.getPrice(), 
        productRequest.getImageUrl());
        products.put(id, product);

        return ResponseEntity.created(URI.create("/api/products/" + id)).build();
    }
}