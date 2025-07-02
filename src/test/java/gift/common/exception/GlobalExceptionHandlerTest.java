package gift.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {GlobalExceptionHandlerTest.TestController.class, GlobalExceptionHandler.class})
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --- 테스트용 DTO ---
    private record TestRequest(
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotNull(message = "나이는 필수입니다.")
            Integer age
    ) {
    }

    @RestController
    public static class TestController {
        @PostMapping("/test/validation")
        public void validation(@Valid @RequestBody TestRequest request) {
        }

        @GetMapping("/test/illegal-argument")
        public void illegalArgument() {
            throw new IllegalArgumentException("잘못된 인자가 전달되었습니다.");
        }

        @GetMapping("/test/missing-param")
        public void missingParam(@RequestParam(required = true) String requiredParam) {
        }

        @GetMapping("/test/generic-exception")
        public void genericException() throws Exception {
            throw new Exception("예상치 못한 에러입니다.");
        }
    }


    @Test
    @DisplayName("MethodArgumentNotValidException 처리 테스트")
    void handleValidationExceptions() throws Exception {
        // given
        TestRequest request = new TestRequest(null, null);

        // when & then
        mockMvc.perform(post("/test/validation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.errors.length()").value(2))
                .andExpect(jsonPath("$.errors[?(@.errorType == 'name')].reason").value("이름은 필수입니다."))
                .andExpect(jsonPath("$.errors[?(@.errorType == 'age')].reason").value("나이는 필수입니다."));
    }

    @Test
    @DisplayName("HttpMessageNotReadableException 처리 테스트 (잘못된 JSON)")
    void handleMalformedJson() throws Exception {
        // given
        String malformedJson = "{\"name\":\"test\", \"age\":}";

        // when & then
        mockMvc.perform(post("/test/validation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("MALFORMED_JSON"))
                .andExpect(jsonPath("$.errors[0].reason").value("유효하지 않은 요청 본문 형식입니다"));
    }

    @Test
    @DisplayName("IllegalArgumentException 처리 테스트")
    void handleIllegalArgumentException() throws Exception {
        // when & then
        mockMvc.perform(get("/test/illegal-argument"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_INPUT"))
                .andExpect(jsonPath("$.errors[0].reason").value("잘못된 인자가 전달되었습니다."));
    }

    @Test
    @DisplayName("MissingServletRequestParameterException 처리 테스트")
    void handleMissingParams() throws Exception {
        // when & then
        mockMvc.perform(get("/test/missing-param"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("MISSING_PARAMETER"))
                .andExpect(jsonPath("$.errors[0].errorType").value("requiredParam"));
    }

    @Test
    @DisplayName("처리되지 않은 모든 예외 처리 테스트")
    void handleGenericException() throws Exception {
        // when & then
        mockMvc.perform(get("/test/generic-exception"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode").value("UNEXPECTED_ERROR"));
    }
}
