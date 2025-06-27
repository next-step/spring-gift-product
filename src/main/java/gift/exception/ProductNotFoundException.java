package gift.exception;

// 제품 검색 실패했을 때 반환하는 예외
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("No product found with id: " + id);
    }

    public ProductNotFoundException() {
        super("No product found");
    }
}