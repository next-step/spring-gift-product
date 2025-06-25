import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    @PostMapping
    public Product create(@RequestBody ProductRequest request) {
        Product product = new Product(nextId++, request.getName(), request.getPrice());
        products.put(product.getId(), product);
        return product;
    }

    @GetMapping("/{productId}")
    public Product read(@PathVariable Long productId) {
        return Optional.ofNullable(products.get(productId))
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));
    }

    @PutMapping("/{productId}")
    public Product update(@PathVariable Long productId, @RequestBody ProductRequest request) {
        Product product = Optional.ofNullable(products.get(productId))
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));
        Product updated = new Product(productId, request.getName(), request.getPrice());
        products.put(productId, updated);
        return updated;
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        if (products.remove(productId) == null) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
    }
}
