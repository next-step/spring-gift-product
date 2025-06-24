package gift.entity;

public record Product(Long id, String name, Long price) {
    public Product (String name, Long price) {
        this(0L, name, price);
    }
}
