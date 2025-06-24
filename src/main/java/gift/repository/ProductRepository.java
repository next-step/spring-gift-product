package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;

public interface ProductRepository {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> findAllProducts();

    ProductResponseDto findProductById(Long id);
}
