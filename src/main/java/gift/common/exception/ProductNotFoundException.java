package gift.common.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "상품을 조회할 수 없습니다.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
