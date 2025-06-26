package gift.common.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageImpl<T> implements Page<T> {

    private final List<T> content;
    private final Pageable pageable;
    private final long total;

    public PageImpl(List<T> content, Pageable pageable, long total) {
        this.content = content;
        this.pageable = pageable;
        this.total = total;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public int getPage() {
        return pageable.getPage();
    }

    @Override
    public int getSize() {
        return pageable.getSize();
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) total / pageable.getSize());
    }

    @Override
    public int getTotalElements() {
        return (int) total;
    }

    @Override
    public boolean hasNext() {
        return getPage() < getTotalPages() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return getPage() > 0;
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        List<U> mappedContent = content.stream()
            .map(converter)
            .collect(Collectors.toList());
        return new PageImpl<>(mappedContent, pageable, total);
    }
}
