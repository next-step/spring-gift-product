package gift.product.repository;

import gift.product.dto.ProductCreateRequest;


public interface ProductRepository {

    String addProduct(ProductCreateRequest dto);
}
