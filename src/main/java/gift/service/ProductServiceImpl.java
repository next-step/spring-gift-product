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

    return new ProductResponse(product);
  }

  @Override
  public void save(ProductRequest request) {
    productRepository.save(productMapper.toEntity(request));
  }

  @Override
  public void update(ProductRequest request) {
    Product product = productRepository.findById(request.getId());
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setImageUrl(request.getImageUrl());
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
