package gift.repository;

import gift.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    Product findProduct(Long id);

    int updateProduct(Long id, String name, Long price, String imageUrl);
}
