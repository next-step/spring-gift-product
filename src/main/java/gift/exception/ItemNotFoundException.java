package gift.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends ApplicationException {

    public ItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다.");
    }
}
