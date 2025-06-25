package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
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
    public Product getById(long id) {
        return repo.findById(id);
    }

    // 등록
    public Product createProduct(Product product) {
        return repo.createProduct(product);
    }
}