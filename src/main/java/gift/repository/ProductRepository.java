package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    // 전체 조회
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    // 단건 조회
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

}
