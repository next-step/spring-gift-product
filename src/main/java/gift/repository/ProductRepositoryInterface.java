package gift.repository;

import gift.entity.Product;
import java.util.Optional;


public interface ProductRepositoryInterface {

    public Optional<Product> findById(long productId);

    public void createProduct(Product product);

    public void updateProduct(Product product);

    public void delete(long productId);

    public boolean containsKey(long id);

}
