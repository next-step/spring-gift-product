package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productList = new HashMap<>();


    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> allProducts = new ArrayList<>();

        for (Product product : productList.values()) {
            ProductResponseDto productResponseDto = new ProductResponseDto(product);
            allProducts.add(productResponseDto);
        }
        return allProducts;
    }

}
