package gift.repository;

import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    Long saveProduct(String name, Integer price, String imageUrl);
    Product findProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product);
    List<Product> findAllProducts();
}
