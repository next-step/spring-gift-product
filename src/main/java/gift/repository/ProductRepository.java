package gift.repository;

import gift.dto.ProductRequestDto;
import gift.entity.Product;

public interface ProductRepository {
    public Product createProduct(ProductRequestDto requestDto);
}
