package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {

  List<ProductResponseDto> findAllProduct();

  ProductResponseDto findProductById(Long id);

  ProductResponseDto createProduct(ProductRequestDto requestDto);

  ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);

  int deleteProduct(Long id);

}
