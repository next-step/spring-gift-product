package gift.repository;


import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Long create(ProductRequestDto requestDto);

    Optional<Product> findById(Long productId);

    int update(Long productId, ProductRequestDto requestDto);

    void delete(Long productId);
}
