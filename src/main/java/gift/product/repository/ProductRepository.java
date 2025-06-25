package gift.product.repository;

import org.springframework.stereotype.Repository;
import gift.product.entity.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    //임시 저장소
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(1L);

    //단건 조회
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    //전체 조회
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    //추가
    public Product save(Product product) {
        Long getId = id.incrementAndGet();
        product.setId(getId);
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    //수정
    public Product update(Product product) {
        if(products.containsKey(product.getId())) {
            return products.put(product.getId(), product);
        }
        return null;
    }

    //삭제
    public void delete(Long id) {
        products.remove(id);
    }


}
