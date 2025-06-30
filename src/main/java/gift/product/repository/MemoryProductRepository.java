package gift.product.repository;

import gift.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryProductRepository implements ProductRepository{
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public Product save(String name, int price, String imageUrl){
        long pId = sequence.incrementAndGet();
        Product product = new Product(pId, name, price, imageUrl);

        products.put(pId, product);

        return product;
    }

    @Override
    public List<Product> findAll(){
        // 전체 조회 (page 등 추후 구현 필요)
        return products.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Product> findById(Long id){
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void update(Long id, String name, int price, String imageUrl) {
        Product target = products.get(id);
        if(target != null){
            target.update(name, price, imageUrl);
        }
    }

    @Override
    public void deleteById(Long id){
        // 존재하지 않으면 무시
        products.remove(id);
    }
}
