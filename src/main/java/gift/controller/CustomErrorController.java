package gift.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (status != null && message != null) {
            model.addAttribute("statusCode", (Integer)status);
            model.addAttribute("errorMessage", message.toString());
        }
        else {
            model.addAttribute("statusCode", 500);
            model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
        }
        return "error";
    }
}
