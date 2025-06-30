package gift.repository;

import gift.domain.Product;
import gift.dto.ProductResponseDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MemoryProductRepository implements ProductRepository {

  private final Map<Long, Product> products = new HashMap<>();

  public final Long InitId = 1L;
  public final Long IncIdCnt = 1L;

  public ProductResponseDto createProduct(Product product) {
    Long productId = products.isEmpty() ? InitId : Collections.max(products.keySet()) + IncIdCnt;
    product.setId(productId);

    products.put(productId, product);

    return new ProductResponseDto(product);
  }

  public List<ProductResponseDto> searchAllProducts() {
    List<ProductResponseDto> allProducts = new ArrayList<>();

    for (Product product : products.values()) {
      ProductResponseDto productResponseDto = new ProductResponseDto(product);
      allProducts.add(productResponseDto);
    }

    return allProducts;
  }

  public Optional<Product> searchProductById(Long id) {
    return Optional.ofNullable(products.get(id));
  }

  public Product updateProduct(Long id, String name, Integer price, String imageUrl) {
    if (!products.containsKey(id)) {
      throw new NoSuchElementException("해당 ID = " + id + " 의 상품이 존재하지 않습니다.");
    }
    Product product = products.get(id);

    product.update(name, price, imageUrl);

    return product;
  }

  public void deleteProduct(Long id) {
    products.remove(id);
  }
}
