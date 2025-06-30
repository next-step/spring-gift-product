// src/main/java/gift/service/ProductServiceImpl.java
package gift.service;

import gift.entity.Product;
import gift.exception.ResourceNotFoundException;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        // id는 null이어야 함
        product.setId(null);
        return repo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다: " + id));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setImageUrl(product.getImageUrl());

        return repo.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("상품을 찾을 수 없습니다: " + id);
        }
        repo.deleteById(id);
    }
}
