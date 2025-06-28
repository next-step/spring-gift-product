package gift.exception;

import gift.controller.AdminProductController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice(assignableTypes = AdminProductController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientErrorException(HttpClientErrorException e, Model model) {
        System.err.println("[Web Exception] API 클라이언트 오류: " + e.getStatusCode() + " - "
                + e.getResponseBodyAsString());
        String errorMessageForUser = "작업 실패: API 서버에서 오류가 발생했습니다.";

        if (e.getStatusCode().is4xxClientError()) {
            errorMessageForUser = switch (e.getStatusCode()) {
                case HttpStatus.BAD_REQUEST -> "입력 데이터가 유효하지 않습니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요.";
                case HttpStatus.NOT_FOUND -> "요청한 리소스를 찾을 수 없습니다.";
                case HttpStatus.CONFLICT -> "이미 존재하는 리소스입니다. 다른 ID를 사용해 주세요.";
                default -> errorMessageForUser;
            };
        }
        model.addAttribute("errorMessage", errorMessageForUser);
        return "error/error_page";
    }

    @ExceptionHandler(ResourceAccessException.class)
    public String handleResourceAccessException(ResourceAccessException e, Model model) {
        System.err.println("[Web Exception] API 서버 연결 오류: " + e.getMessage());
        model.addAttribute("errorMessage", "API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요.");
        return "error/error_page";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e, Model model) {
        System.err.println("[Web Exception] 예상치 못한 오류: " + e.getMessage());
        e.printStackTrace();
        model.addAttribute("errorMessage", "예상치 못한 오류가 발생했습니다.");
        return "error/error_page";
    }
}
