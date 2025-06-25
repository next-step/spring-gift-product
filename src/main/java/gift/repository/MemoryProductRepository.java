package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryProductRepository implements ProductRepository {

  private final Map<Long, Product> productMap = new HashMap<>();

  public ProductResponseDto createProduct(Product product) {
    Long productId = productMap.isEmpty() ? 1 : Collections.max(productMap.keySet()) + 1;
    product.setId(productId);

    productMap.put(productId, product);

    return new ProductResponseDto(product);
  }

  public List<ProductResponseDto> searchAllProducts() {
    List<ProductResponseDto> allProducts = new ArrayList<>();

    for (Product product : productMap.values()) {
      ProductResponseDto productResponseDto = new ProductResponseDto(product);
      allProducts.add(productResponseDto);
    }

    return allProducts;
  }

  public Optional<Product> searchProductById(Long id) {
    return Optional.ofNullable(productMap.get(id));
  }

  public Product updateProduct(Long id, String name, Integer price, String imageUrl) {
    if (!productMap.containsKey(id)) {
      throw new NoSuchElementException("해당 ID = " + id + " 의 상품이 존재하지 않습니다.");
    }
    Product product = productMap.get(id);

    product.updateInfo(name, price, imageUrl);

    return product;
  }

  public void deleteProduct(Long id) {
    productMap.remove(id);
  }
}
