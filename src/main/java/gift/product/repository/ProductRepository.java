package gift.product.repository;

import gift.product.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    public boolean save(Product product){
        if (products.containsKey(product.getId())){
            return false;
        }
        products.put(product.getId(), product);
        return true;
    }

    public Product get(Long id){
        return products.get(id);
    }
}
