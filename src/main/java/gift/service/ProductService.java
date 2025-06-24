package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductRequestDto productRequestDto);

    ProductResponseDto findProductById(Long id);

    List<ProductResponseDto> findAllProducts();

    void updateProduct(Long productId, ProductRequestDto requestDto);

    void deleteProductById(Long id);
}
