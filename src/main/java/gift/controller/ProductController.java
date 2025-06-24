package gift.controller;

import gift.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {

    }

    @GetMapping
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }
}

