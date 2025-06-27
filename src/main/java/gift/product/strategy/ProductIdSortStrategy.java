package gift.product.strategy;

import gift.common.strategy.SortStrategy;
import gift.product.domain.Product;
import java.util.Comparator;

public class ProductIdSortStrategy implements SortStrategy<Product> {
  @Override
  public Comparator<Product> getComparator() {
    return Comparator.comparing(Product::id);
  }
}
