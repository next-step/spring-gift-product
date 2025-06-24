package gift;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/product")
    public String createProduct(@RequestBody Product product) {
        products.put(product.getId(), product);
        return "상품 생성: " + product.getId();
    }
}
