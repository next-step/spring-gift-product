package gift.repository;

import gift.entity.Product;

public interface ProductRepository {
    public Product addProduct(String name, Long price, String url);
    public Product findProductById(Long id);
    public void updateProductById(Product newProduct);
    public void deleteProductById(Long id);
}
