package gift.Model;

public class Price {
    private final int value;

    public Price(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
