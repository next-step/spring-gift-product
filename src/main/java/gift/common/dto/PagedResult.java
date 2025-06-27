package gift.common.dto;

import java.util.List;

public record PagedResult<T>(
    List<T> content,
    int page,
    int size,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
) {

  public static <T> PagedResult<T> from(List<T> content, PagedResult<?> original) {
    return new PagedResult<>(
        content,
        original.page(),
        original.size(),
        original.totalElements(),
        original.totalPages(),
        original.first(),
        original.last()
    );
  }
}
