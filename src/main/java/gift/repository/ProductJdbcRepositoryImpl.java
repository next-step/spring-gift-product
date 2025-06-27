package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;

public class ProductJdbcRepositoryImpl implements ProductRepository {

  @Override
  public List<ProductResponseDto> findAllProduct() {
    return List.of();
  }

  @Override
  public ProductResponseDto findProductById(Long id) {
    return null;
  }

  @Override
  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    return null;
  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
    return null;
  }

  @Override
  public void deleteProduct(Long id) {

  }
}
