package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductResponse::new)
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
    }

    public ProductResponse addProduct(ProductRequest request) {
        Product product = new Product(request.name(), request.price(),
                request.imageUrl());
        Product saved = productRepository.save(product);
        return new ProductResponse(saved);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product updated = productRepository.update(id, request.name(), request.price(),
                request.imageUrl());
        return updated != null ? new ProductResponse(updated) : null;
    }

    public boolean deleteProduct(Long id) {
        return productRepository.delete(id);
    }


}
