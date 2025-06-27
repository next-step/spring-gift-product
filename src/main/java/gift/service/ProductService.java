package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductResponseDto> findAll();

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    ProductResponseDto findProduct(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);

    void deleteProduct(Long id);
}
