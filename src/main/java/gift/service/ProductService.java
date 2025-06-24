package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ProductResponse> findAll() {
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product product : products.values()) {
            ProductResponse response = new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            );
            responseList.add(response);
        }
        return responseList;
    }

    public ProductResponse save(ProductRequest request) {
        Long id = idGenerator.getAndIncrement();
        Product product = new Product(id, request.getName(), request.getPrice(), request.getImageUrl());
        products.put(id, product);
        return new ProductResponse(id, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setImageUrl(request.getImageUrl());
            return new ProductResponse(id, product.getName(), product.getPrice(), product.getImageUrl());
        }
        throw new NoSuchElementException("상품이 존재하지 않습니다.");
    }

    public void delete(Long id) {
        products.remove(id);
    }
}
