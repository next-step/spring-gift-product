package gift.service;

import gift.dto.ProductRequestDto;
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

    // 추가
    public Product createProduct(ProductRequestDto req) {
        Product product = new Product(null, req.name(), req.price(), req.imageUrl());
        return repo.createProduct(product);
    }

    // 수정
    public Product updateProduct(Long id, ProductRequestDto req) {
        Product updatedProduct = new Product(id, req.name(), req.price(), req.imageUrl());
        return repo.updateProduct(id, updatedProduct);
    }

    // 삭제
    public boolean delete(Long id) {
        if (repo.findById(id) == null) {
            return false;
        }
        repo.delete(id);
        return true;
    }
}