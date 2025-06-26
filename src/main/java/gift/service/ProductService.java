package gift.service;

import gift.dto.CreateProductRequestDto;
import gift.dto.UpdateProductRequestDto;
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

    public Product create(CreateProductRequestDto dto) {
        return repository.save(new Product(null, dto.getName(), dto.getPrice(), dto.getImageUrl()));
    }

    public Optional<Product> update(Long id, UpdateProductRequestDto dto) {
        if (repository.findById(id).isEmpty())
        {
            return Optional.empty();
        }
        Product updated = new Product(id, dto.getName(), dto.getPrice(), dto.getImageUrl());
        repository.update(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(Long id) {
        if (repository.findById(id).isEmpty())
        {
            return false;
        }
        repository.delete(id);
        return true;
    }

}
