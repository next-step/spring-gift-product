package gift.product.service;

import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;

public interface ProductService {

    public String addProduct(ProductCreateRequest dto);
}
