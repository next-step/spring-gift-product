package gift.dto;

import java.util.List;
import java.util.stream.Collectors;

public record CustomPage<T> (
    List<T> contents,
    Integer page,
    Integer size,
    Integer totalElements,
    Integer totalPages
) {
    public static class Builder<T> {
        private List<T> contents;
        private Integer page = 0;
        private Integer size = 5; // 기본값 설정

        public Builder(List<T> contents) {
            if (contents == null) {
                throw new IllegalArgumentException("목록은 null일 수 없습니다!");
            }
            this.contents = contents;
        }

        public Builder<T> page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder<T> size(Integer size) {
            this.size = size;
            return this;
        }

        public CustomPage<T> build() {
            int totalElements = contents.size();
            if (this.page == null || this.size == null) {
                throw new IllegalArgumentException("페이지 번호와 크기는 생략할 수 없습니다!");
            }
            if (this.page < 0 || this.size <= 0) {
                throw new IllegalArgumentException("페이지 번호는 0 이상이어야 하고, 크기는 1 이상이어야 합니다!");
            }
            int totalPages = (int) Math.ceil((double) totalElements / size);
            if (this.page >= totalPages) {
                throw new IllegalArgumentException("페이지 번호가 총 페이지 수를 초과했습니다!");
            }
            int offset = page * size;
            this.contents = contents.stream()
                    .skip(offset)
                    .limit(size)
                    .collect(Collectors.toList());
            return new CustomPage<>(this.contents, this.page, this.size, totalElements, totalPages);
        }
    }
}
