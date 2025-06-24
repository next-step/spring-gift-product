package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Long, Product> productList = new HashMap<>();

    @Override
    public List<ProductResponseDto> findAllProducts() {

        return productList.values()
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }
}
