package gift.controller;

import gift.dto.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    @GetMapping
    public Collection<Product> getProducts() {
        return products.values();
    }


}