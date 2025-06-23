package gift.service;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.CreateProductResponse;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

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
        Optional<Product> findProduct = repository.findById(id);
        if (findProduct.isEmpty()) {
            throw new NoSuchElementException("존재하지 않는 상품입니다.");
        }
        return repository.findById(id).get();


    }
}
