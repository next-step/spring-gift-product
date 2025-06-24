package gift.service;

import gift.controller.ProductController;
import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(CreateProductRequestDto requestDto);
    List<ProductResponseDto> findAllProducts();
    ProductResponseDto findProductById(Long id);
    ProductResponseDto updateProductById(Long id, CreateProductRequestDto requestDto);
    void deleteProductById(Long id);
}
