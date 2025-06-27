package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> update(Long id, Product product) {
    if (!productRepository.exists(id)) {
      return Optional.empty();
    }
    return Optional.of(productRepository.update(id, product));
  }

  public boolean delete(Long id) {
    if (!productRepository.exists(id)) {
      return false;
    }
    productRepository.delete(id);
    return true;
  }
}
