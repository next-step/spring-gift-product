package gift.global.common.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public record PagedResult<T>(
    List<T> content,
    int page,
    int size,
    long totalElements,
    int totalPages,
    boolean isFirst,
    boolean isLast
) {

  public static <T> PagedResult<T> of(List<T> content, int page, int size) {
    long totalElements = content.size();
    int totalPages = (int) Math.ceil((double) totalElements / size);
    boolean isFirst = page == 0;
    boolean isLast = page >= totalPages - 1;

    return new PagedResult<>(content, page, size, totalElements, totalPages, isFirst, isLast);
  }

  public <U> PagedResult<U> map(Function<T, U> mapper) {
    List<U> mappedContent = this.content.stream()
        .map(mapper)
        .collect(Collectors.toList());

    return new PagedResult<>(mappedContent, page, size, totalElements, totalPages, isFirst, isLast);
  }
}
