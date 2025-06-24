package gift.repository;

import gift.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> productMap = new HashMap<>();
    private Long nextId=1L;

    public Product addProduct(Product product){
        product.setId(nextId++);
        productMap.put(product.getId(), product);
        return product;
    }
}
