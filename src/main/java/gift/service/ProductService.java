package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import java.util.List;

public interface ProductService {

  ProductResponse getProductById(Long productId);
  void save(ProductRequest request);
  void update(ProductRequest request);
  void deleteById(Long productId);

  List<ProductResponse> getProductList();

}
