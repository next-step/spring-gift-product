package gift.common;

import gift.common.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {

    public static <T> Response<T> ok(T value, String message){
        return new Response<>(0,message,value);
    }

    public static <T> Response<T> error(ErrorCode errorCode){
        return new Response<>(errorCode.getCode(),errorCode.getMessage(),null);
    }
}