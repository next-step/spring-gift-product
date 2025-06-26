package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Product newProduct = new Product(id, product.getName(), product.getPrice(),
                product.getImageUrl());
        products.put(id, newProduct);
        return newProduct;
    }

    public Product update(Long id, String name, int price, String imageUrl) {
        Product product = products.get(id);
        if (product == null) {
            return null; // 없는 ID는 무시
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
