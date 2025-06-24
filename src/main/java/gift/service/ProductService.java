package gift.service;

import gift.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long productId);
    Product create(Product product);
}
