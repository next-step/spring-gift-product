package gift.repository;

import gift.dto.ProductAddRequestDto;
import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    public void addProduct(ProductAddRequestDto requestDto);
    public Product findProductById(Long id);
    public List<Product> findAllProduct();
    public void updateProductById(Product product);
    public void deleteProductById(Long id);
}
