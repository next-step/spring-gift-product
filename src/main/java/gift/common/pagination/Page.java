package gift.common.pagination;

import java.util.List;
import java.util.function.Function;

public interface Page<T> {
    List<T> getContent();

    int getPage();
    
    int getSize();

    int getTotalPages();

    int getTotalElements();

    boolean hasNext();

    boolean hasPrevious();

    <U> Page<U> map(Function<? super T, ? extends U> converter);
}
