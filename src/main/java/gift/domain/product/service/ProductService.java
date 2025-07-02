package gift.domain.product.service;
import gift.common.pagination.Page;
import gift.common.pagination.Pageable;
import gift.domain.product.dto.ProductRequest;
import gift.domain.product.dto.ProductResponse;

public interface ProductService {

    Page<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse getProductById(Long id);

    ProductResponse addProduct(ProductRequest productRequest);

    void updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

}
