package gift.common;

public record ApiResponse<T>(
    int status,
    String message,
    T data
) {

}
