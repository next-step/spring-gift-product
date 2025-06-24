package gift.product.service;

import gift.product.dto.ProductCreateRequestDto;
import gift.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 0;

    public void createProduct(ProductCreateRequestDto product) {
        final long newProductId = ++nextId;
        products.put(newProductId, new Product(
                newProductId,
                product.name(),
                product.price(),
                product.imageUrl()
        ));
    }
}
