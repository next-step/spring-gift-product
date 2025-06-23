package gift.product.repository;

import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ProductRepositoryV1 implements ProductRepository{
    private final Map<String, Product> products = new HashMap<>();

    public String addProduct(ProductCreateRequest dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getImageURL());

        products.put(product.getId(), product);

        return product.getId();
    }

    public List<Product> findAll() {
        return products.values()
                .stream()
                .toList();
    }


    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }
}
