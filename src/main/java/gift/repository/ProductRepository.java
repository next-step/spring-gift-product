package gift.repository;

import gift.entity.Product;
import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    Product findProduct(Long productId);

    Product deleteProduct(Long productId);

    List<Product> findAllProducts();

}
