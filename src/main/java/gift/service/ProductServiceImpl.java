package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductRequest;
import gift.dto.response.ProductResponse;
import gift.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idgen = new AtomicLong(1);

    @Override
    public ProductResponse register(ProductRequest request) {
        Long id = idgen.getAndIncrement();
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return new ProductResponse(product);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        return new ProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return products.values().stream()
                .map(ProductResponse::new)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        Product exist = products.get(productId);
        if (exist == null) {
            throw new ProductNotFoundException(productId);
        }

        if (request.name() != null && !request.name().isBlank()) {
            exist.setName(request.name());
        }
        if (request.price() > 0) {
            exist.setPrice(request.price());
        }
        if (request.imageUrl() != null && !request.imageUrl().isBlank()) {
            exist.setImageUrl(request.imageUrl());
        }

        return new ProductResponse(exist);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product exist = products.get(productId);
        if (exist == null) {
            throw new ProductNotFoundException(productId);
        }
        products.remove(productId);
    }
}
