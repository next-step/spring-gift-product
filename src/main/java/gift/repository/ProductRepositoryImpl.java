package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
  //코드리뷰: 동시에 접근해도 safe한 concurrenthashmap사용

  @Override
  public List<ProductResponseDto> findAllProduct() {
    List<ProductResponseDto> responseDtos = new ArrayList<>();
    for (Product product : productMap.values()) {
      ProductResponseDto responseDto = new ProductResponseDto(product);
      responseDtos.add(responseDto);
    }
    return responseDtos;
  }

  @Override
  public ProductResponseDto findProductById(Long id) {
    Product product = productMap.get(id);
    ProductResponseDto responseDto = new ProductResponseDto(product);
    return responseDto;
  }

  @Override
  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    Product product = new Product(requestDto);
    if (productMap.get(product.getId()) == null) {
      productMap.put(product.getId(), product);
    } else {
      //코드리뷰: 이미 존재하는 id는 예외처리
      throw new IllegalStateException("already exist id");
    }
    ProductResponseDto responseDto = new ProductResponseDto(product);
    return responseDto;
  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
    Product product = new Product(requestDto);
    productMap.put(id, product);
    ProductResponseDto responseDto = new ProductResponseDto(product);
    return responseDto;
  }

  @Override
  public void deleteProduct(Long id) {
    productMap.remove(id);
  }
}
