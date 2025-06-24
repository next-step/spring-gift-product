package gift.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
@Order
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    1. 어플리케이션 공통 예외
     */
    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleNpe(NullPointerException ex, HttpServletRequest req) {
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, "NULL 값이 발생했습니다")
                .property("code", ErrorCode.NULL_ERROR.name())
                .property("field", ErrorField.NULL_POINTER.name())
                .property("path", req.getRequestURI())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArg(IllegalArgumentException ex, HttpServletRequest req) {
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                .property("code", ErrorCode.INVALID_INPUT.name())
                .property("field", ErrorField.INVALID_ARGUMENT.name())
                .property("path", req.getRequestURI())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleUnexpected(Exception ex, HttpServletRequest req) {
        return ErrorResponse.builder(ex, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다")
                .property("code", ErrorCode.UNEXPECTED_ERROR.name())
                .property("path", req.getRequestURI())
                .build();
    }

    /*
    2.스프링 MVC 표준 예외 (override)
    */

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorResponse err = ErrorResponse.builder(ex, status,
                        "필수 파라미터 '%s'가 누락되었습니다".formatted(ex.getParameterName()))
                .property("code", ErrorCode.MISSING_PARAMETER.name())
                .property("parameter", ex.getParameterName())
                .build();

        ProblemDetail body = err.updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorResponse err = ErrorResponse.builder(ex, status, "잘못된 JSON 형식입니다")
                .property("code", ErrorCode.MALFORMED_JSON.name())
                .build();

        ProblemDetail body = err.updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBind(BindException ex, HttpServletRequest req) {
        List<ProblemDetail> fieldErrors = ex.getFieldErrors().stream()
                .map(fe -> {
                    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, fe.getDefaultMessage());
                    pd.setProperty("field", fe.getField());
                    pd.setProperty("rejected", fe.getRejectedValue());
                    return pd;
                })
                .toList();

        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, "바인딩에 실패했습니다")
                .property("code", ErrorCode.BINDING_FAILED.name())
                .property("errors", fieldErrors)
                .property("path", req.getRequestURI())
                .build();
    }

}
