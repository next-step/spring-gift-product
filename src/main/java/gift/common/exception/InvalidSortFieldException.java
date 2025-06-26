package gift.common.exception;


import lombok.Getter;

@Getter
public class InvalidSortFieldException extends RuntimeException {
    private final ErrorCode errorCode;
    public InvalidSortFieldException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
