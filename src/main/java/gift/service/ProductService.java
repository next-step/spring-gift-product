package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {
    public ProductResponseDto createProduct(ProductRequestDto requestDto);
    public List<ProductResponseDto> findAllProducts();
}
