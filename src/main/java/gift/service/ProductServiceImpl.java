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
    //requestDto의 id와 path로 들어오는 id가 다른 경우.
    if (requestDto.getId() != id) {
      throw new IllegalStateException("request id와 실제 id가 일치하지 않습니다");
    }

    return repository.updateProduct(id, requestDto);
  }

  @Override
  public void deleteProduct(Long id) {
    repository.deleteProduct(id);
  }


}
