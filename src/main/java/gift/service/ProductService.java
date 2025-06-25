package gift.service;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductUpdateRequestDto;

public interface ProductService {
    public ProductResponseDto addProduct(ProductAddRequestDto requestDto);
    public ProductResponseDto findProductById(Long id);
    public ProductResponseDto updateProductById(Long id, ProductUpdateRequestDto requestDto);
    public void deleteProductById(Long id);
}
