package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }


  @Override
  public List<ProductResponseDto> findAllProduct() {
    return repository.findAllProduct();
  }

  @Override
  public ProductResponseDto findProductById(Long id) {
    return repository.findProductById(id);
  }

  @Override
  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    return repository.createProduct(requestDto);
  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
    return repository.updateProduct(id, requestDto);
  }

  @Override
  public int deleteProduct(Long id) {
    return repository.deleteProduct(id);
  }


}
