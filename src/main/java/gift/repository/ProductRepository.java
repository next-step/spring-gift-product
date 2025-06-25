package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductRepository() {
        products.put(1L,
                new Product(1L, "아이스 아메리카노", 4500L,
                        "ice"));
        products.put(2L, new Product(2L, "초코라떼", 5500L,
                "chchch"));
    }

    // 전체 조회
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    // 단건 조회
    public Product findById(Long id) {
        return products.get(id);
    }
}

