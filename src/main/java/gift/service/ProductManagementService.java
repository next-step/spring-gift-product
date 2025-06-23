package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductManagementService {

    private final ProductRepository productRepository;

    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(ProductRequest request) {
        Product newProduct = Product.of(
                request.name(),
                request.price(),
                request.imageUrl()
        );
        productRepository.save(newProduct);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public void update(Long id, ProductRequest request) {
        Product updatedProduct = Product.of(
                request.name(),
                request.price(),
                request.imageUrl()
        );
        productRepository.update(id, updatedProduct);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
