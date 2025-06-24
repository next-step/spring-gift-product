package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository {

  private final Map<Long, Product> products = new HashMap<>();
  private Long sequence = 2L; // ID 자동 증가용 (초기값 2, 이미 1번 있음)

  public ProductRepository() {
    // 초기 더미 데이터
    products.put(1L, new Product(1L, "아이스 카페 아메리카노 T", 4500,
        "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
  }

  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

  public Optional<Product> findById(Long id) {
    return Optional.ofNullable(products.get(id));
  }


  public Product save(Product product) {
    Long id = sequence++;
    Product newProduct = new Product(id, product.getName(), product.getPrice(),
        product.getImageUrl());
    products.put(id, newProduct);
    return newProduct;
  }

}