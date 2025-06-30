package gift.repository;

import gift.dto.PageRequestDto;
import gift.dto.PageResult;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

public interface ProductRepository {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    PageResult<ProductResponseDto> findAllProducts(PageRequestDto pageRequestDto);

    ProductResponseDto findProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProduct(Long id);
}
