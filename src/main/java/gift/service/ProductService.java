package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAllProducts();

    ProductResponseDto saveProduct(ProductRequestDto dto);
    ProductResponseDto findProductById(Long id);
    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    void deleteProduct(Long id);
}
