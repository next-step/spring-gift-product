package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import java.util.List;

public interface ProductService {
    ProductResponse getProductById(Long productId);

    Long save(ProductRequest request);

    void update(ProductRequest request);

    void deleteById(Long productId);

    List<ProductResponse> getProductList();

}
