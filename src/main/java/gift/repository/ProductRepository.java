package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private Long id = 1L;
    private final Map<Long,Product> products = new HashMap<>();

    public Product findById(Long id) {
        return products.get(id);
    }

    public Product saveProduct(String name, int price, String imageUrl) {
        Product product = new Product(id++, name,price,imageUrl);
        products.put(product.getId(),product);
        return product;
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
}
