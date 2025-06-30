package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long sequence = 1L;

    public ProductRepository() {
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }


    public Product save(Product product) {
        Long id = sequence++;
        Product newProduct = product.assignId(id);
        products.put(id, newProduct);
        return newProduct;
    }

    public Product update(Long id, String name, int price, String imageUrl) {
        Product product = products.get(id);
        if (product == null) {
            throw new NoSuchElementException("해당 ID의 상품이 존재하지 않습니다...");
        }

        Product updated = new Product(id, name, price, imageUrl);
        products.put(id, updated);
        return updated;
    }

    public boolean delete(Long id) {
        if (!products.containsKey(id)) {
            return false;
        }
        products.remove(id);
        return true;
    }
}
