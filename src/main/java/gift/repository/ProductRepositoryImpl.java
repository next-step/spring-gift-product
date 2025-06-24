package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Long id = requestDto.getId();
        String name = requestDto.getName();
        Integer price = requestDto.getPrice();
        String imageUrl = requestDto.getImageUrl();

        Product product = new Product(id, name, price, imageUrl);
        products.put(id, product);
        return new ProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productList = new ArrayList<>();
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(new ProductResponseDto(entry.getValue()));
        }

        return productList;
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = products.get(id);
        return new ProductResponseDto(product);
    }
}
