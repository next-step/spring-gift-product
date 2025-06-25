package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // 컬렉션을 활용하여 임시로 구현
    final private Map<Long, Product> productMap;
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
        productMap.put(1L,
                new Product(1L, "Nike Air MX Super 2500 - Red", 200000L, "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        );
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

    @Override
    public Product update(Product product) {
        productMap.put(product.getId(), product); // 제품을 업데이트
        return product; // 업데이트된 제품 반환
    }

    @Override
    public Boolean deleteById(Long productId) {
        if (productMap.containsKey(productId)) {
            productMap.remove(productId); // 제품을 삭제
            return true; // 영향을 받은 행 수 반환
        }
        return false; // 제품이 존재하지 않으면 0 반환
    }
}
