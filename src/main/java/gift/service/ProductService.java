package gift.service;

import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }


    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public void save(Product product) {
        long id = repo.save(product);
        product.setId(id);
    }

    public boolean update(Long id, Product product) {
        return repo.update(id, product);
    }

    public boolean delete(Long id) {
        return repo.delete(id);
    }
}
