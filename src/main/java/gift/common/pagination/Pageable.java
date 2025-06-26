package gift.common.pagination;

public class Pageable {

    private final int page;
    private final int size;

    public Pageable(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("페이지 번호는 0보다 작을 수 없다.");
        }
        if (size < 1) {
            throw new IllegalArgumentException("페이지 크기는 1보다 작을 수 없다.");
        }
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return page * size;
    }
}
