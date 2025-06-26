package gift.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductRequestDto(
        @NotBlank(message = "상품 이름은 필수입니다.")
        String name,
        @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
        Long price,
        @NotBlank(message = "이미지 URL은 필수입니다.")
        String imageUrl
) {

}
