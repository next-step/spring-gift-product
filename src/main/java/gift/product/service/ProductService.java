package gift.product.service;


import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductResponse;
import gift.product.dto.ProductUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    UUID addProduct(ProductCreateRequest dto);
    List<ProductResponse> findAllProducts();
    ProductResponse findProduct(UUID id);
    void deleteProduct(UUID id);
    void updateProduct(UUID id, ProductUpdateRequest dto);
}
