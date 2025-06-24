package gift.repository;

import gift.entity.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private final Map<Long, Product> products = new HashMap<>();
    @Override
    public Product createProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
        return newProduct;
    }
}
