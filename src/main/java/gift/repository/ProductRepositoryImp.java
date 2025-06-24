package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImp implements ProductRepository{
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public List<ProductResponseDto> findAllProducts() {
        /*
        임시 데이터
        Product product1 = new Product(1L, "test1", 12000L, "temp1");
        Product product2 = new Product(2L, "test2", 13000L, "temp2");
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
         */

        return products.values()
                .stream()
                .map(ProductResponseDto::toDto)
                .toList();
    }
}
