package gift.exception;

public class FailedGenerateKeyException extends IllegalStateException {
    public FailedGenerateKeyException() {
        super("키 생성에 실패했습니다.");
    }
}
