package gift.product.repository;

import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class ProductRepositoryV1 implements ProductRepository{
    private final Map<String, Product> products = new HashMap<>();

    public String addProduct(ProductCreateRequest dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getImageURL());

        products.put(product.getId(), product);

        return product.getId();
    }
}
