package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private long sequence = 0L;

    public Product save(Product product){
        long newId = ++sequence;

        Product newProduct = product.withId(newId);
        products.put(newId, newProduct);
        return newProduct;
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public void deleteById(Long id){
        products.remove(id);
    }

}
