package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // 임시 DB
    private final Map<Long, Product> products = new HashMap<>();

    // id값
    private long nextId = 1;

    @Override
    public Product saveProduct(Product product) {
        long productId = nextId++;
        product.setId(productId);
        products.put(productId, product);

        return product;
    }

    @Override
    public Product findProduct(long productId) {

        return products.get(productId);
    }

    @Override
    public Product deleteProduct(long productId) {
        return products.remove(productId);
    }

    @Override
    public List<Product> findAllProducts() {

        return new ArrayList<>(products.values());
    }

}
