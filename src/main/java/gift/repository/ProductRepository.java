package gift.repository;

import gift.entity.Product;
import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    Product findProduct(long productId);

    Product deleteProduct(long productId);

    List<Product> findAllProducts();

}
