package gift.product.repository;

import gift.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    public Product save(String name, int price, String imageUrl);

    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public void deleteById(Long id);
}
