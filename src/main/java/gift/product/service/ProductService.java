package gift.product.service;

import gift.product.Product;
import gift.product.dto.ProductRequest;
import gift.product.dto.ProductResponse;
import gift.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public ResponseEntity<ProductResponse> addProduct(ProductRequest req) {
        Product product = new Product(req.name(), req.price(), req.imageUrl());
        boolean result = productRepository.save(product);
        if (result) {
            return new ResponseEntity<>(ProductResponse.from(product), HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
