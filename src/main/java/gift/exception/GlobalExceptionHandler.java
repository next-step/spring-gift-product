package gift.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotExistException.class)
    public ModelAndView handleProductNotExist() {

        Map<String, String> model = new HashMap<>();
        model.put("errorMessage", "상품이 존재하지 않습니다.");

        return new ModelAndView("error/product-not-found", model);
    }
}