package gift.service;

import gift.domain.Product;
import gift.dto.ProductMapper;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.repository.ProductRepositoryImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepositoryImpl productRepository;
  private final ProductMapper productMapper;

  public ProductServiceImpl(ProductRepositoryImpl productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public ProductResponse getProductById(Long productId) {
    Product product = productRepository.findById(productId);

    if (product == null) {
      return null;
    }

    return ProductResponse.of(product.getName(), product.getPrice(), product.getImageUrl());
  }

  @Override
  public void save(ProductRequest request) {
    productRepository.save(productMapper.toEntity(request));
  }

  @Override
  public void update(ProductRequest request) {
    Product product = productRepository.findById(request.id());
    product.update(request.name(), request.price(), request.imageUrl());
    productRepository.save(product);
  }

  @Override
  public void deleteById(Long productId) {
    productRepository.deleteById(productId);
  }

  @Override
  public List<ProductResponse> getProductList() {
    return productRepository.findAll().stream()
        .map(productMapper::toResponse)
        .toList();
  }
}
