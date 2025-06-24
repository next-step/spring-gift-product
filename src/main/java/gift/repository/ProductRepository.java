package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    // ID 관리
    private long temp_id = 1L;

    // 전체 조회
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    // 단건 조회
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    // 상품 생성
    public Product save(Product product){
        product.setId(temp_id++);
        products.put(product.getId(), product);
        return product;
    }

    // 상품 수정
    public void update(Long id, Product product){
        products.put(id, product);
    }

}
