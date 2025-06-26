package gift.service;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.CreateProductResponse;
import gift.dto.UpdateProductRequest;
import gift.dto.UpdateProductResponse;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public CreateProductResponse save(CreateProductRequest request) {
        Product product = repository.save(request);
        return new CreateProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public Product findById(Long id) {
        findIdOrThrow(id);
        return repository.findById(id).get();


    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public UpdateProductResponse update(Long id, UpdateProductRequest request) {
        findIdOrThrow(id);
        Product updateProduct = repository.update(id, request);
        return new UpdateProductResponse(updateProduct.getId(), updateProduct.getName(), updateProduct.getPrice(), updateProduct.getImageUrl());

    }

    public void delete(Long id) {
        findIdOrThrow(id);
        repository.delete(id);

    }

    private void findIdOrThrow(Long id) {
        Optional<Product> findProduct = repository.findById(id);
        if (findProduct.isEmpty()) {
            throw new NoSuchElementException("존재하지 않는 상품입니다.");
        }
    }
}
