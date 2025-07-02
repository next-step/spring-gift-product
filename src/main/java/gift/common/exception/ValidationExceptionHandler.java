package gift.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
@Order(2)
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

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
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), errorCode.getMessage());
        body.setProperty("code", errorCode.name());
        body.setProperty("errors", fieldErrors);
        body.setProperty("path", request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorCode errorCode = ErrorCode.MISSING_PARAMETER;
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(),
                        "필수 파라미터 '%s'가 누락되었습니다".formatted(ex.getParameterName()));
        body.setProperty("code", errorCode.name());
        body.setProperty("parameter", ex.getParameterName());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorCode errorCode = ErrorCode.MALFORMED_JSON;
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), errorCode.getMessage());
        body.setProperty("code", errorCode.name());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBind(BindException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.BINDING_FAILED;
        List<ProblemDetail> fieldErrors = ex.getFieldErrors().stream()
                .map(fe -> {
                    ProblemDetail pd = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), fe.getDefaultMessage());
                    pd.setProperty("field", fe.getField());
                    pd.setProperty("rejected", fe.getRejectedValue());
                    return pd;
                })
                .toList();

        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), errorCode.getMessage());
        body.setProperty("code", errorCode.name());
        body.setProperty("errors", fieldErrors);
        body.setProperty("path", req.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }
} 