package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> findAllProducts();
    ProductResponseDto findProductById(Long id);
    ProductResponseDto saveProduct(ProductRequestDto dto);
    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);
    void deleteProduct(Long id);
}
