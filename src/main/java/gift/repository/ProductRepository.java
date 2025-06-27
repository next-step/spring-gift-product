package gift.repository;


import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductRepository {

    List<Product> findAll();

    Long createProduct(ProductRequestDto requestDto);

    Product findProduct(Long productId);

    int updateProduct(Long productId, ProductRequestDto requestDto);

    void deleteProduct(Long productId);
}
