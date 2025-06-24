package gift.common.exception;

import gift.common.code.CustomResponseCode;

public class CustomException extends RuntimeException {

    private final CustomResponseCode errorCode;

    public CustomException(CustomResponseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomResponseCode getErrorCode() {
        return errorCode;
    }
}
