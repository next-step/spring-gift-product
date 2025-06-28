package gift.repository;

import gift.dto.ProductAddRequestDto;
import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    public int addProduct(Product product);
    public Product findProductByIdOrElseThrow(Long id);
    public List<Product> findAllProduct();
    public int updateProductById(Product product);
    public int deleteProductById(Long id);
}
