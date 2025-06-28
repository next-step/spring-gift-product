package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductRequest;
import gift.dto.response.ProductResponse;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse register(ProductRequest request) {
        Product product = new Product(null, request.name(), request.price(), request.imageUrl());
        productRepository.register(product);
        return new ProductResponse(product);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return new ProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::new)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        Product exist = productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));

        String name     = (request.name() != null && !request.name().isBlank()) ? request.name() : exist.getName();
        int price       = (request.price() > 0) ? request.price() : exist.getPrice();
        String imageUrl = (request.imageUrl() != null && !request.imageUrl().isBlank()) ? request.imageUrl() : exist.getImageUrl();

        Product updated = new Product(productId,name,price,imageUrl);

        productRepository.update(productId, updated);

        return new ProductResponse(updated);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (productRepository.findById(productId).isEmpty()){
            throw new ProductNotFoundException(productId);
        }
        productRepository.delete(productId);
    }

    @Override
    public List<ProductResponse> searchByName(String keyword) {
        return productRepository.searchByName(keyword).stream()
                .map(ProductResponse::new)
                .toList();
    }
}
