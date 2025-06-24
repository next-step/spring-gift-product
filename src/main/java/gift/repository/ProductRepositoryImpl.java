package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return products.values().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = products.get(id);

        if (product == null) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다. id=" + id);
        }

        return new ProductResponseDto(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl());
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}
