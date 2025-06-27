package gift.repository;


import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductRepository {

    List<ProductResponseDto> findAll();

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    ProductResponseDto findProduct(Long productId);

    ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto);

    void deleteProduct(Long productId);
}
