package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    public Product createProduct(ProductRequestDto requestDto);
    public List<ProductResponseDto> findAllProducts();
}
