package gift.service;

import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {

        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("해당 상품이 존재하지 않습니다.");
        }
        productRepository.deleteById(id);
    }

    public void update(Long id, Product updatedProduct) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("해당 상품이 존재하지 않습니다.");
        }
        Product updated = new Product(
                id,
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getImageUrl()
        );
        productRepository.save(updatedProduct);
    }


}

