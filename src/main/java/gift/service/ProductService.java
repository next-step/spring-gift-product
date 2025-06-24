package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {
    public ProductResponseDto createProduct(ProductRequestDto requestDto);
    public List<ProductResponseDto> findAllProducts();
    public ProductResponseDto findProductById(Long id);
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);
}
