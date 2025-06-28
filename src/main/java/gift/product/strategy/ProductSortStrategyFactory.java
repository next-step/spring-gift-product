package gift.product.strategy;

import gift.common.dto.SortInfo;
import gift.common.exception.ErrorCode;
import gift.common.exception.InvalidSortFieldException;
import gift.common.strategy.SortStrategy;
import gift.product.domain.Product;
import java.util.Comparator;
import java.util.Map;

public class ProductSortStrategyFactory {
  private static final Map<String, SortStrategy<Product>> strategyMap = Map.of(
      "id", new ProductIdSortStrategy(),
      "name", new ProductNameSortStrategy(),
      "price", new ProductPriceSortStrategy()
  );

  private static SortStrategy<Product> getStrategy(String sortField) throws InvalidSortFieldException {
    SortStrategy<Product> strategy = strategyMap.get(sortField);
    if (strategy == null) {
      throw new InvalidSortFieldException(ErrorCode.INVALID_SORT_FIELD_ERROR);
    }
    return strategy;
  }

  public static Comparator<Product> getComparator(SortInfo sortInfo){
    String sortField = sortInfo.field();
    boolean isAscending = sortInfo.isAscending();

    SortStrategy<Product> sortStrategy = ProductSortStrategyFactory.getStrategy(sortField);

    return isAscending ?
        sortStrategy.getComparator() :
        sortStrategy.getComparator().reversed();
  }
}
