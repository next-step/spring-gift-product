package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<ProductResponseDto> findAllProducts();
    ProductResponseDto findProductByIdOrElseThrow(Long id);
    ProductResponseDto createProduct(String name, Long price, String imageUrl);
    void deleteProduct(Long id);
}
