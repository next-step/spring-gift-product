package gift.service;

import gift.dto.ProductResponse;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream()
        .map(ProductResponse::new)
        .collect(Collectors.toList());
  }

  public ProductResponse getProductById(Long id) {
    return productRepository.findById(id)
        .map(ProductResponse::new)
        .orElse(null);
  }
}