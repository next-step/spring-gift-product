package gift.dto.common;

import java.util.List;

public record Page<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {

    public Page(List<T> content, int page, int size, long totalElements) {
        this(
                content,
                page,
                size,
                totalElements,
                (int) Math.ceil((double) totalElements / size),
                (long) page * size < totalElements
        );
    }
}
