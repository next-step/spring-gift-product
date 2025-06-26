package gift.service;

import gift.dto.CustomPage;
import gift.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    CustomPage<Product> getBy(int page, int size);
    Product getById(Long productId);
    Product create(Product product);
    Product update(Product product);
    void deleteById(Long productId); // 삭제 메소드 추가
}
