package gift.product.service;

import gift.product.Product;
import gift.product.dto.ProductRequest;
import gift.product.dto.ProductResponse;
import gift.product.dto.ProductUpdateRequest;
import gift.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse addProduct(ProductRequest req) {
        Product product = new Product(req.name(), req.price(), req.imageUrl());
        Optional<Product> optionalProduct = productRepository.save(product);
        if (optionalProduct.isPresent()) {
            return ProductResponse.from(optionalProduct.get());
        }
        throw new RuntimeException("ProductService : addProduct() failed - 500 Internal Server Error");
    }

    public ProductResponse getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.get(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("ProductService : getProduct() failed - 404 Not Found Error");
        }
        Product product = optionalProduct.get();
        return ProductResponse.from(product);
    }

    @Transactional
    public void updateProduct(Long id, ProductUpdateRequest req) {
        int affectedRows = productRepository.update(id, req);
        if (affectedRows == 0) {
            throw new RuntimeException("ProductService : updateProduct() failed - 500 Internal Server Error");
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.get(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("ProductService : deleteProduct() failed - 404 Not Found Error");
        }
        productRepository.delete(id);
    }

    public List<ProductResponse> getAllProducts() {
        Optional<List<Product>> optionalProducts = productRepository.getAll();
        if (optionalProducts.isEmpty()) {
            throw new RuntimeException("ProductService : getAllProducts() failed - 404 Not Found Error");
        }
        return optionalProducts.get()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }
}
