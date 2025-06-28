package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong initId = new AtomicLong();

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(initId.incrementAndGet(),
                productRequestDto.name(),
                productRequestDto.price(),
                productRequestDto.imageUrl()
        );
        products.put(initId.get(), product);
        return new ProductResponseDto(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl());
    }

    @Override
    public Page<ProductResponseDto> findAllProducts(Pageable pageable) {
        List<Product> allProducts = new ArrayList<>(products.values());

        int total = allProducts.size();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startIdx = currentPage * pageSize;
        int endIdx = Math.min(startIdx + pageSize, total);

        List<ProductResponseDto> pageList = new ArrayList<>();
        if (startIdx < total) {
            pageList = allProducts.subList(startIdx, endIdx).stream()
                    .map(this::toResponseDto)
                    .toList();
        }

        return new PageImpl<>(pageList, pageable, total);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
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
            throw new ProductNotFoundException(id);
        }

        product.update(
                productRequestDto.name(),
                productRequestDto.price(),
                productRequestDto.imageUrl()
        );
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    @Override
    public void deleteProduct(Long id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException(id);
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
