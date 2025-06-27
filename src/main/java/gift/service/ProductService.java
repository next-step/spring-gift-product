package gift.service;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {
    public void addProduct(ProductAddRequestDto requestDto);
    public ProductResponseDto findProductById(Long id);
    public List<ProductResponseDto> findAllProduct();
    public void updateProductById(Long id, ProductUpdateRequestDto requestDto);
    public void deleteProductById(Long id);
}
