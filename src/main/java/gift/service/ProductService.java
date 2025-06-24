package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDto saveProduct(ProductRequestDto requestDto);

    List<ProductResponseDto> findAllProducts();

    ProductResponseDto findProductById(Long id);

    ProductResponseDto updateProduct(Long id,ProductRequestDto requestDto);

    void deleteProduct(Long id);



}
