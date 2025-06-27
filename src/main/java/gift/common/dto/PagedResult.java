package gift.common.dto;

import java.util.List;

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
}
