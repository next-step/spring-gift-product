package gift.product.repository;

import gift.domain.Product;
import gift.global.exception.NotFoundProductException;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductRepositoryV1 implements ProductRepository{
    private final Map<UUID, Product> products = new HashMap<>();

    public UUID save(Product product) {

        products.put(product.getId(), product);

        return product.getId();
    }

    public List<Product> findAll() {
        return products.values()
                .stream()
                .toList();
    }


    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }


    public void deleteById(UUID id) {
        if (products.containsKey(id)) products.remove(id);
        else throw new NotFoundProductException("삭제 실패 - 존재하지 않는 상품입니다");
    }


    public void update(Product product) {
        UUID id = product.getId();
        if (!products.containsKey(id)) throw new NotFoundProductException("수정 실패 - 존재하지 않는 상품입니다");

        products.put(id, product);
    }
}
