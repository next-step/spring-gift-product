package gift.domain;

public record Product(Long id, String name, Integer price, String imageUrl) {

    public Product {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("제품명은 비어있거나 null일 수 없습니다.");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("imageUrl은 비어있거나 null일 수 없습니다.");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("가격은 null이거나 음수일 수 없습니다.");
        }
    }

    public static Product of(String name, Integer price, String imageUrl) {
        return new Product(null, name, price, imageUrl);
    }

    public static Product withId(Long id, String name, Integer price, String imageUrl) {
        if (id == null) {
            throw new IllegalArgumentException("ID는 null일 수 없습니다.");
        }
        return new Product(id, name, price, imageUrl);
    }

    public Product update(String name, Integer price, String imageUrl) {
        return new Product(this.id, name, price, imageUrl);
    }
}
