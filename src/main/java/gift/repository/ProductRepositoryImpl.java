package gift.repository;

import gift.dto.PageRequestDto;
import gift.dto.PageResult;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
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
    public PageResult<ProductResponseDto> findAllProducts(PageRequestDto pageRequestDto) {
        List<Product> allProducts = new ArrayList<>(products.values());

        int total = allProducts.size();
        int pageSize = pageRequestDto.size();
        int currentPage = pageRequestDto.page();
        int startIdx = pageSize * currentPage;
        int endIdx = Math.min(startIdx + pageSize, total);

        List<ProductResponseDto> pageList = new ArrayList<>();
        if (startIdx < total) {
            pageList = allProducts.subList(startIdx, endIdx).stream()
                    .map(this::toResponseDto)
                    .toList();
        }

        int totalPages = (int) Math.ceil((double) total / pageSize);

        return new PageResult<>(pageList, currentPage, totalPages, pageSize, total);
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
