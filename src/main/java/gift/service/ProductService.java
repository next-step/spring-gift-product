package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
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

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductResponse::new)
                .orElse(null);
    }

    public ProductResponse addProduct(ProductRequest request) {
        Product product = new Product(null, request.getName(), request.getPrice(),
                request.getImageUrl());
        Product saved = productRepository.save(product);
        return new ProductResponse(saved);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product updated = productRepository.update(id, request.getName(), request.getPrice(),
                request.getImageUrl());
        return updated != null ? new ProductResponse(updated) : null;
    }

    public boolean deleteProduct(Long id) {
        return productRepository.delete(id);
    }


}