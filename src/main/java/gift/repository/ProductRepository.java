package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private final Map<Long, Product> products = new HashMap<>();

  public ProductRepository() {
    // 초기 더미 데이터
    products.put(1L, new Product(1L, "아이스 카페 아메리카노 T", 4500,
        "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
  }

  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }
}