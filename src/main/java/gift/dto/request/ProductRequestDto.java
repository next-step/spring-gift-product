package gift.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ProductRequestDto(
        @NotBlank(message = "상품 이름을 입력해 주세요.")
        String name,

        @NotNull(message = "가격을 입력해 주세요.")
        @Min(value = 0, message = "가격은 0원 이상부터 입력 가능합니다.")
        Long price,

        @NotBlank(message = "이미지 URL을 입력해 주세요.")
        @URL(message = "올바른 URL 형식이 아닙니다. 예시: http://....")
        String imageUrl) { }
