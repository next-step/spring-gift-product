package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductRepository {

  List<ProductResponseDto> findAllProduct();

  ProductResponseDto findProductById(Long id);

  ProductResponseDto createProduct(ProductRequestDto requestDto);

  ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);

  void deleteProduct(Long id);

}
