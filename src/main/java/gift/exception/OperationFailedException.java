package gift.exception;

public class OperationFailedException extends RuntimeException{
    public OperationFailedException() {
        super("작업이 성공적으로 완료되지 않았습니다.");
    }
}
