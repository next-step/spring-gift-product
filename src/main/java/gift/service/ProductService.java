package gift.service;

import gift.dto.ProductRequest;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.name() == null || request.price() == null || request.imageUrl() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        if (request.id() != null && productRepository.findById(request.id()).isPresent()) {
            throw new IllegalArgumentException("ID(" + request.id() + ") already exists");
        }

        Product product = new Product(
            request.id(),
            request.name(),
            request.price(),
            request.imageUrl()
        );
        return productRepository.save(product);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(
                () -> new IllegalArgumentException("Product(id: " + productId + ") not found"));
    }

    public Product updateProduct(Long productId, ProductRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (productRepository.findById(productId).isEmpty()) {
            throw new IllegalArgumentException("Product(id: " + productId + ") not found");
        }
        Product existingProduct = productRepository.findById(productId).get();

        String updatedName = existingProduct.getName();
        if (request.name() != null) {
            updatedName = request.name();
        }

        int updatedPrice = existingProduct.getPrice();
        if (request.price() != null) {
            updatedPrice = request.price();
        }

        String updatedImageUrl = existingProduct.getImageUrl();
        if (request.imageUrl() != null) {
            updatedImageUrl = request.imageUrl();
        }

        Product updatedProduct = new Product(
            productId,
            updatedName,
            updatedPrice,
            updatedImageUrl
        );
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long productId) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new IllegalArgumentException("Product(id: " + productId + ") not found");
        }
        productRepository.delete(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}