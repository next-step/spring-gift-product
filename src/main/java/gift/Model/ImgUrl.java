package gift.Model;

public class ImgUrl {
    private final String value;

    public ImgUrl(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("이미지 URL은 비어 있을 수 없습니다.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
