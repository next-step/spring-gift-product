package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

public interface ProductRepository {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

}
