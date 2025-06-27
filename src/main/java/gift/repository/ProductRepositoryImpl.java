package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // DB connection 하지 않음에 따라 Collection인 HashMap을 사용 (DB 연결 시 없어질 예정)
    private final Map<Long, Product> products = new HashMap<>();
    private long productId = 1L;

    @Override
    public List<ProductResponseDto> findAll() {
        List<ProductResponseDto> productList = new ArrayList<>();

        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(
                ProductResponseDto.from(entry.getValue())
            ); // Product -> ProductResponseDto
        }
        return productList;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        products.put(productId,
            new Product(requestDto.id(), requestDto.name(), requestDto.price(),
                requestDto.imageUrl()));
        productId++; // id 값 1 증가

        return new ProductResponseDto(requestDto.id(), requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl());
    }

    @Override
    public ProductResponseDto findProduct(Long productId) {
        Product product = products.get(productId);
        return ProductResponseDto.from(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto) {
        Product product = products.get(productId);

        // product 내용 update
        product.setId(requestDto.id());
        product.setName(requestDto.name());
        product.setPrice(requestDto.price());
        product.setImageUrl(requestDto.imageUrl());

        products.put(productId, product);

        return ProductResponseDto.from(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (products.remove(productId) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<Long, Product> findAllMap() {
        return products;
    }
}
