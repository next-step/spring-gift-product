package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    // DB 대체 제품 저장 컬렉션
    private final Map<Long, Product> producsts = new HashMap<>();
}
