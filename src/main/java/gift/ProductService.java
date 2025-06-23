package gift;

import gift.dto.CreateProductDto;
import gift.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductCollector productCollector;

    public ProductService(ProductCollector productCollector) {
        this.productCollector = productCollector;
    }

    public ProductDto createProduct(CreateProductDto requestBody) {
        Product instance = requestBody.createInstance();
        Product created = productCollector.add(instance);
        return ProductDto.from(created);
    }
}
