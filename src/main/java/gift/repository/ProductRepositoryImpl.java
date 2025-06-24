package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Long id = requestDto.id();
        String name = requestDto.name();
        Integer price = requestDto.price();
        String imageUrl = requestDto.imageUrl();

        Product product = new Product(id, name, price, imageUrl);
        products.put(id, product);

        return ProductResponseDto.from(product);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productList = new ArrayList<>();
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(ProductResponseDto.from(entry.getValue()));
        }

        return productList;
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return ProductResponseDto.from(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = new Product(
            id,
            requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl());
        products.put(id, product);

        return ProductResponseDto.from(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (products.remove(id) == null) {
            throw new ProductNotFoundException(id);
        }
    }
}
