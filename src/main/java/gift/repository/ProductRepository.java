package gift.repository;

import gift.entity.Product;

public interface ProductRepository {
    public Product addProduct(String name, Long price, String url);
}
