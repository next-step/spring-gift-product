package gift.product.service;

import gift.product.Product;
import gift.product.dto.ProductRequest;
import gift.product.dto.ProductResponse;
import gift.product.dto.ProductUpdateRequest;
import gift.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse addProduct(ProductRequest req) {
        Product product = new Product(req.name(), req.price(), req.imageUrl());
        boolean result = productRepository.save(product);
        if (result) {
            return ProductResponse.from(product);
        }
        throw new RuntimeException("ProductService : addProduct() failed - 500 Internal Server Error");
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.get(id);
        if (product == null) {
            throw new RuntimeException("ProductService : getProduct() failed - 404 Not Found Error");
        }
        return ProductResponse.from(product);
    }

    public ProductResponse updateProduct(Long id, ProductUpdateRequest req) {
        Product product = productRepository.get(id);
        if (product == null) {
            throw new RuntimeException("ProductService : updateProduct() failed - 404 Not Found Error");
        }
        product.update(req);
        return ProductResponse.from(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.get(id);
        if (product == null) {
            throw new RuntimeException("ProductService : deleteProduct() failed - 404 Not Found Error");
        }
        productRepository.delete(id);
    }
}
