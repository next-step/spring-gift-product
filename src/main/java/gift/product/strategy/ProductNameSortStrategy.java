package gift.product.strategy;

import gift.global.common.strategy.SortStrategy;
import gift.product.domain.Product;
import java.util.Comparator;

public class ProductNameSortStrategy implements SortStrategy<Product> {
  @Override
  public Comparator<Product> getComparator() {
    return Comparator.comparing(Product::name);
  }
}
