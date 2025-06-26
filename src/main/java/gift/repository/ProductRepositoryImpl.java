package gift.repository;

import gift.dto.ResponseDto;
import gift.entity.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product saveProduct(Product product) {

        long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;

        product.setProductId(productId);

        products.put(productId, product);

        return product;
    }

    @Override
    public List<ResponseDto> findAllProducts() {

        List<ResponseDto> allProducts = products.values().stream()
            .map(product -> new ResponseDto(product))
            .collect(Collectors.toList());

        return allProducts;
    }


}
