package gift.repository;

import gift.entity.Product;
import java.util.Optional;


public interface ProductRepositoryInterface {

    Optional<Product> findById(long productId);

    void createProduct(Product product);

    void updateProduct(Product product);

    void delete(long productId);

    boolean containsKey(long id);

}
