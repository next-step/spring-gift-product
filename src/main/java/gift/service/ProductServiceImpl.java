package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong autoIncrementId = new AtomicLong(1);

    @Override
    public ProductResponse create(ProductRequest request) {

        Long id = autoIncrementId.getAndIncrement();
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);

        return ProductResponse.from(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return products.values().stream()
            .map(ProductResponse::from)
            .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProduct(Long id) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("조회하신 상품이 존재하지 않습니다.");
        }

        return ProductResponse.from(product);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }

        product.update(request.name(), request.price(), request.imageUrl());

        return ProductResponse.from(product);
    }

    @Override
    public void delete(Long id) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }

        products.remove(id);
    }
}
