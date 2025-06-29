package gift.front.exception;

import gift.api.dto.ErrorResponseDto;
import gift.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FrontExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(
            ProductNotFoundException ex,
            Model model,
            HttpServletRequest request) {

        ex.printStackTrace();

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        model.addAttribute("errorInfo", error);

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception ex,
            Model model,
            HttpServletRequest request) {

        ex.printStackTrace();

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getRequestURI()
        );
        model.addAttribute("errorInfo", error);

        return "error";
    }
}