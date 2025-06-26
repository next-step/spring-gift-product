package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
    public Page<ProductResponseDto> findAllProducts(Pageable pageable) {
        List<ProductResponseDto> all = products.values().stream()
                .map(p -> new ProductResponseDto(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()))
                .toList();

        int total = all.size();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startIdx = currentPage * pageSize;
        int endIdx = Math.min(startIdx + pageSize, total);

        List<ProductResponseDto> pageList = new ArrayList<>();
        if (startIdx < total) {
            pageList = all.subList(startIdx, endIdx);
        }

        return new PageImpl<>(pageList, pageable, total);
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

        product.update(
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
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
