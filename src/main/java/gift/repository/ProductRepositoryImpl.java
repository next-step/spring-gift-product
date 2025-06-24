package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // 컬렉션을 활용하여 임시로 구현
    final private HashMap<Long, Product> productMap;
    private Long sequence; // ID 생성에 사용될 시퀀스

    public ProductRepositoryImpl() {
        this.productMap = new HashMap<>();
        this.sequence = 0L;

        testInit();
    }

    // 테스트 용으로 초기 데이터를 설정
    private void testInit() {
        productMap.put(8146027L,
                new Product(8146027L, "Gift Card", 10000L, "https://example.com/giftcard.jpg"));
    }

    @Override
    public List<Product> findAll() {
        return productMap.values().stream().toList();
    }

    @Override
    public Product findById(Long productId) {
        return productMap.get(productId);
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(++sequence); // ID가 없으면 시퀀스를 증가시켜 새 ID를 할당
        }
        productMap.put(product.getId(), product); // 제품을 저장
        return product; // 저장된 제품 반환
    }
}
