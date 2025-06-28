package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
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

    public ProductResponse createProduct(ProductRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        Product product = new Product(
            null,
            request.name(),
            request.price(),
            request.imageUrl()
        );
        product = productRepository.save(product);
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );
    }

    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(
                () -> new IllegalArgumentException("Product(id: " + productId + ") not found"));
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );
    }

    public ProductResponse updateProduct(Long productId, ProductRequest request) {
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
        updatedProduct = productRepository.save(updatedProduct);
        return new ProductResponse(
            updatedProduct.getId(),
            updatedProduct.getName(),
            updatedProduct.getPrice(),
            updatedProduct.getImageUrl()
        );
    }

    public void deleteProduct(Long productId) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new IllegalArgumentException("Product(id: " + productId + ") not found");
        }
        productRepository.delete(productId);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
            .map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
            ))
            .toList();
    }
}