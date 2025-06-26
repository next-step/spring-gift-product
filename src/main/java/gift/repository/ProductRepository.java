package gift.repository;

import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    public Product addProduct(String name, Long price, String url);
    public Product findProductById(Long id);
    public List<Product> findAllProduct();
    public void updateProductById(Product newProduct);
    public void deleteProductById(Long id);
}
