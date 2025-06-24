package gift.repository;

import gift.model.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<Long, Product> producsts = new HashMap<>();
    private Long sequence = 0L;

    public Product save(Product product) {
        product.setId(++sequence);
        producsts.put(product.getId(), product);
        return product;
    }

}
