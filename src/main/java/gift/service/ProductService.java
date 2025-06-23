package gift.service;

import gift.entity.Product;
import org.springframework.stereotype.Service;
import gift.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }




}
