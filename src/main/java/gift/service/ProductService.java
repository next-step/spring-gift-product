package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;

public interface ProductService {

    ProductResponse create(ProductRequest request);
}
