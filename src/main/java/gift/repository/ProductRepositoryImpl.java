package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
    private AtomicLong initId = new AtomicLong();

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(initId.incrementAndGet(),
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
        );
        products.put(initId.get(), product);
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

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = products.get(id);

        if (product == null) {
            throw new NoSuchElementException("수정할 상품이 없습니다. id=" + id);
        }

        Product updateProduct = new Product(id,
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
        );
        products.put(id, updateProduct);

        return new ProductResponseDto(updateProduct.getId(),
                updateProduct.getName(),
                updateProduct.getPrice(),
                updateProduct.getImageUrl());
    }

    @Override
    public void deleteProduct(Long id) {
        if (!products.containsKey(id)) {
            throw new NoSuchElementException("삭제하고자 하는 상품이 존재하지 않습니다. id=" + id);
        }

        products.remove(id);
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
