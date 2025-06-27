package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {

  ProductResponseDto createProduct(ProductRequestDto productRequestDto);

  List<ProductResponseDto> searchAllProducts();

  ProductResponseDto searchProductById(Long id);

  ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

  void deleteProduct(Long id);
}
