package gift.common.dto;

import gift.common.code.CustomResponseCode;

public record CustomResponseBody<T>(
    int status,
    String message,
    T data
) {

    public static <T> CustomResponseBody<T> of(CustomResponseCode code, T data) {
        return new CustomResponseBody<>(code.getCode(), code.getMessage(), data);
    }

    public static CustomResponseBody<Void> of(CustomResponseCode code) {
        return new CustomResponseBody<>(code.getCode(), code.getMessage(), null);
    }
}
