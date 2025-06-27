package gift.product.strategy;

import gift.common.exception.ErrorCode;
import gift.common.exception.InvalidSortFieldException;
import gift.common.strategy.SortStrategy;
import gift.product.domain.Product;
import java.util.Map;

public class ProductSortStrategyFactory {
  private static final Map<String, SortStrategy<Product>> strategyMap = Map.of(
      "id", new ProductIdSortStrategy(),
      "name", new ProductNameSortStrategy(),
      "price", new ProductPriceSortStrategy()
  );

  public static SortStrategy<Product> getStrategy(String sortField) {
    SortStrategy<Product> strategy = strategyMap.get(sortField);
    if (strategy == null) {
      throw new InvalidSortFieldException(ErrorCode.INVALID_SORT_FIELD_ERROR);
    }
    return strategy;
  }
}
