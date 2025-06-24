package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long initId = 1L;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(initId++,
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
        );
        products.put(initId++, product);
        return new ProductResponseDto(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl());
    }
}
