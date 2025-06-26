package gift.dto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomPage<T> {
    private final List<T> contents;
    private final Integer page;
    private final Integer size;
    private final Integer totalElements;
    private final Integer totalPages;

    public CustomPage(List<T> base, Integer page, Integer size) {
        if (page == null || size == null) {
            throw new IllegalArgumentException("페이지 번호와 크기는 생략할 수 없습니다!");
        }
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("페이지 번호는 0 이상이어야 하고, 크기는 1 이상이어야 합니다!");
        }
        this.page = page;
        this.size = size;
        this.totalElements = base.size();
        this.totalPages = (int) Math.ceil((double) totalElements / size);

        if (this.page >= totalPages) {
            throw new IllegalArgumentException("페이지 번호가 총 페이지 수를 초과했습니다!");
        }
        int offset = page * size;
        this.contents = base.stream()
                .skip(offset)
                .limit(size)
                .collect(Collectors.toList()
        );
    }

    public List<T> getContents() {
        return contents;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
