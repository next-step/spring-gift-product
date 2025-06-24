package gift.repository;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long sequence = 0L;

    // 상품 저장
    public Product save(Product product) {
        product.setId(++sequence);
        products.put(product.getId(), product);
        return product;
    }

    // 상품 전체 조회
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    // 상품 단건 조회
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    // 상품 수정
    public void update(Product product) {
        products.put(product.getId(), product);
    }

}
