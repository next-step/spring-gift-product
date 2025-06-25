package gift.service;

import gift.dto.ProductRequestDto;
import gift.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequestDto productRequestDto);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, ProductRequestDto productRequestDto);
    void deleteProduct(Long id);
}
