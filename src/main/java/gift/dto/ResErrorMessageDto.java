package gift.dto;

public class ResErrorMessageDto {
    private final String message;

    public ResErrorMessageDto(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
