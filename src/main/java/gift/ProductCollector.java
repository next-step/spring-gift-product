package gift;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductCollector {

    private Long id = 0L;
    private final Map<Long, Product> map = new HashMap<>();

    public Product add(Product product) {
        product.setId(id);
        map.put(id++, product);
        return product;
    }

    public Product get(Long id) {
        return map.get(id);
    }

    public List<Product> getAll() {
        return map.values().stream()
                .sorted(Comparator.comparing(Product::getId))
                .collect(Collectors.toList());
    }

    public void update(Long id, Product product) {
        product.setId(id);
        map.put(id, product);
    }

    public void delete(Long id) {
        map.remove(id);
    }
}
