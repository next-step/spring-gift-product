package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    Long nextId = 1L;

    public ProductResponseDto save(Product product) {
        Long id  =  nextId++;
        products.put(id, product);
        return new ProductResponseDto(id, product.getName(), product.getPrice(), product.getImageUrl());
    }
}
