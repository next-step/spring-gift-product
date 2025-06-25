package gift.service;

import gift.dto.ProductUpdateRequestDto;
import gift.entity.Product;

import gift.repository.ProductRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product registerProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));
    }

    public Product updateProduct(long id, ProductUpdateRequestDto updateRequestDto) {
        Product existing = repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));

        repository.update(id, updateRequestDto);
        return existing;
    }

    public void deleteProduct(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        repository.delete(id);
    }

}
