package gift.common.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "존재하지 않는 상품입니다.";

    public ProductNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + " 상품 id = " + id);
    }
}
