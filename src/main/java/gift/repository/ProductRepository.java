package gift.repository;


import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductRepository {

    List<Product> findAll();

    Long create(ProductRequestDto requestDto);

    Product find(Long productId);

    int update(Long productId, ProductRequestDto requestDto);

    void delete(Long productId);
}
