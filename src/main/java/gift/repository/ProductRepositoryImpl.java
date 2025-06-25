package gift.repository;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long currentProductId = 1L;

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return products.values().stream()
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findProductById(Long id) throws IllegalArgumentException {
        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다: " + id);
        }

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(
                currentProductId++,
                productRequestDto.name(),
                productRequestDto.price(),
                productRequestDto.imageUrl());

        products.put(product.getId(), product);

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto)
            throws IllegalArgumentException {
        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다: " + id);
        }

        product.setName(productRequestDto.name());
        product.setPrice(productRequestDto.price());
        product.setImageUrl(productRequestDto.imageUrl());

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    @Override
    public void deleteProduct(Long id) throws IllegalArgumentException {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다: " + id);
        }

        products.remove(id);
    }
}
