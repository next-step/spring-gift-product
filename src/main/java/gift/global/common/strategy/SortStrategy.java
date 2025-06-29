package gift.global.common.strategy;

import java.util.Comparator;

public interface SortStrategy<T> {
  Comparator<T> getComparator();
}
