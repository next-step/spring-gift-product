package gift.Model;

public class ProductName {
    private final String value;

    public ProductName(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("상품 이름은 비어 있을 수 없습니다.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
