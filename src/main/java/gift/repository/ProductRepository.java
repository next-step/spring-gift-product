package gift.repository;

import gift.entity.Product;

public interface ProductRepository {
    public Product addProduct(String name, Long price, String url);
    public Product findProductById(Long id);
    public Product updateProduct(Long id, String name, Long price, String url);
}
