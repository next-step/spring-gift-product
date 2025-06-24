package gift.repository;

import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long productId);
    Product save(Product product);
    Product update(Product product);
    Long deleteById(Long productId); // 삭제 메소드 추가
}
