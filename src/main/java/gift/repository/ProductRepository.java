package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    public ProductResponseDto createProduct(ProductRequestDto requestDto);
    public List<ProductResponseDto> findAllProducts();
    public ProductResponseDto findProductById(Long id);
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);
}
