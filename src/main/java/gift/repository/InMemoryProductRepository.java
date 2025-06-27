package gift.repository;

import gift.model.Product;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong sequenceGenerator = new AtomicLong(10000L);

    @PostConstruct
    public void init() {
        products.put(8146027L, new Product(8146027L, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        products.put(1234567L, new Product(1234567L, "제주 한라봉 스무디", 6000,
                "https://tester.com/hallabong.jpg"));
        products.put(9876543L, new Product(9876543L, "리얼 초코 케이크", 15000,
                "https://example.com/chocolate_cake.jpg"));
        sequenceGenerator.set(
                products.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1);
        System.out.println(
                "InMemoryProductRepository: 초기 상품 데이터가 로드되었습니다. 총 상품 수: " + products.size());
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            Long newId = sequenceGenerator.incrementAndGet();
            Product newProduct = new Product(
                    newId, product.getName(), product.getPrice(), product.getImageUrl());
            products.put(newProduct.getId(), newProduct);
            return newProduct;
        } else {
            products.put(product.getId(), product);
            return product;
        }
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
}
