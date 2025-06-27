package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
    Product product = new Product(productRequestDto.name(), productRequestDto.price(),
        productRequestDto.imageUrl());
    return productRepository.createProduct(product);
  }

  public List<ProductResponseDto> searchAllProducts() {
    return productRepository.searchAllProducts();
  }

  public ProductResponseDto searchProductById(Long id) {
    Optional<Product> optionalProduct = productRepository.searchProductById(id);

    Product product = optionalProduct.orElseThrow(() ->
        new NoSuchElementException("해당 ID = " + id + " 의 상품이 존재하지 않습니다.")
    );

    return new ProductResponseDto(product);
  }

  public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {

    Product updated = productRepository.updateProduct(
        id,
        productRequestDto.name(),
        productRequestDto.price(),
        productRequestDto.imageUrl()
    );

    return new ProductResponseDto(updated);
  }

  public void deleteProduct(Long id) {
    productRepository.deleteProduct(id);
  }
}
