package gift.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
@Order
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleNpe(NullPointerException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.NULL_ERROR;
        return ErrorResponse.builder(ex, errorCode.getHttpStatus(), errorCode.getMessage())
                .property("code", errorCode.name())
                .property("path", req.getRequestURI())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArg(IllegalArgumentException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        return ErrorResponse.builder(ex, errorCode.getHttpStatus(), ex.getMessage())
                .property("code", errorCode.name())
                .property("path", req.getRequestURI())
                .build();
    }

    @ExceptionHandler(NumberFormatException.class)
    public ErrorResponse handleNumberFormat(NumberFormatException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        return ErrorResponse.builder(ex, errorCode.getHttpStatus(), "올바르지 않은 숫자 형식입니다.")
                .property("code", errorCode.name())
                .property("path", req.getRequestURI())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        return ErrorResponse.builder(ex, errorCode.getHttpStatus(), 
                String.format("'%s' 파라미터 값이 올바르지 않습니다.", ex.getName()))
                .property("code", errorCode.name())
                .property("parameter", ex.getName())
                .property("rejectedValue", ex.getValue())
                .property("path", req.getRequestURI())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleUnexpected(Exception ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.UNEXPECTED_ERROR;
        return ErrorResponse.builder(ex, errorCode.getHttpStatus(), errorCode.getMessage())
                .property("code", errorCode.name())
                .property("path", req.getRequestURI())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<ProblemDetail> fieldErrors = ex.getFieldErrors().stream()
                .map(fe -> {
                    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, fe.getDefaultMessage());
                    pd.setProperty("field", fe.getField());
                    pd.setProperty("rejectedValue", fe.getRejectedValue());
                    return pd;
                })
                .toList();

        ErrorCode errorCode = ErrorCode.VALIDATION_FAILED;
        ErrorResponse err = ErrorResponse.builder(ex, errorCode.getHttpStatus(), errorCode.getMessage())
                .property("code", errorCode.name())
                .property("errors", fieldErrors)
                .property("path", request.getDescription(false).replace("uri=", ""))
                .build();

        ProblemDetail body = err.updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, body, headers, errorCode.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorCode errorCode = ErrorCode.MISSING_PARAMETER;
        ErrorResponse err = ErrorResponse.builder(ex, errorCode.getHttpStatus(),
                        "필수 파라미터 '%s'가 누락되었습니다".formatted(ex.getParameterName()))
                .property("code", errorCode.name())
                .property("parameter", ex.getParameterName())
                .build();

        ProblemDetail body = err.updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, body, headers, errorCode.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorCode errorCode = ErrorCode.MALFORMED_JSON;
        ErrorResponse err = ErrorResponse.builder(ex, errorCode.getHttpStatus(), errorCode.getMessage())
                .property("code", errorCode.name())
                .build();

        ProblemDetail body = err.updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, body, headers, errorCode.getHttpStatus(), request);
    }

    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBind(BindException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.BINDING_FAILED;
        List<ProblemDetail> fieldErrors = ex.getFieldErrors().stream()
                .map(fe -> {
                    ProblemDetail pd = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), fe.getDefaultMessage());
                    pd.setProperty("field", fe.getField());
                    pd.setProperty("rejected", fe.getRejectedValue());
                    return pd;
                })
                .toList();

        return ErrorResponse.builder(ex, errorCode.getHttpStatus(), errorCode.getMessage())
                .property("code", errorCode.name())
                .property("errors", fieldErrors)
                .property("path", req.getRequestURI())
                .build();
    }
}
