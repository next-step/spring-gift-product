package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<ProductResponseDto> findAllProducts();
    ProductResponseDto findProductByIdElseThrow(Long id);
    ProductResponseDto saveProduct(String name, Long price, String imageUrl);
}
