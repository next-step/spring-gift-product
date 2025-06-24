package gift.repository;

import gift.dto.CreateProductRequestDto;
import gift.entity.Product;

public interface ProductRepository {
    Product createProduct(Product newProduct);
}
