package gift.product.service;


import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductResponse;
import gift.product.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    String addProduct(ProductCreateRequest dto);
    List<ProductResponse> findAllProducts();
    ProductResponse findProduct(String id);
    void deleteProduct(String id);
    void updateProduct(String id, ProductUpdateRequest dto);
}
