package gift.domain.product.controller;

import gift.domain.product.model.Product;
import gift.domain.product.dto.AddProductRequest;
import gift.domain.product.dto.ProductResponse;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (productRequest.getPrice() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        if (productRequest.getName() == null || productRequest.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (productRequest.getImageUrl() == null || productRequest.getImageUrl().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Product product = new Product(id, 
        productRequest.getName(), 
        productRequest.getPrice(), 
        productRequest.getImageUrl());
        products.put(id, product);

        return ResponseEntity.created(URI.create("/api/products/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> productResponses = products.values().stream()
            .map(ProductResponse::from)
            .toList();
        if (productResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductResponse.from(product));
    }
}