package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // 전체 조회
    public List<Product> getAll() {
        return repo.findAll();
    }

    // 단건 조회
    public Optional<Product> getById(Long id) {
        return repo.findById(id);
    }

    // 추가
    public Product createProduct(Product product) {
        return repo.save(product);
    }

    // 수정
    public Optional<Product> update(Long id, Product product) {
        return repo.update(id, product);
    }

    // 삭제
    public boolean delete(Long id) {
        Optional<Product> existing = repo.findById(id);
        if (existing.isEmpty()) {
            return false;
        }
        repo.delete(id);
        return true;
    }
}