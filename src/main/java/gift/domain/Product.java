package gift.domain;

public record Product(String name, Integer price, String imageUrl) {

    public static Product of(String name, Integer price, String imageUrl) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
        return new Product(name, price, imageUrl);
    }
}
