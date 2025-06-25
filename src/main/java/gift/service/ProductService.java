package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {

    List<ProductResponseDto> findAllProducts();

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    ProductResponseDto findProduct(Long productId);

    ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto);

    void deleteProduct(Long productId);
}
