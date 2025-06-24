package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto requestDto);

    ProductResponseDto getProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);
}
