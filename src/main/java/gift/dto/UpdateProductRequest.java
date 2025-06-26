package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProductRequest (
        Long id,
        @NotBlank(message = "상품명은 필수입니다.")
        String name,
        @Positive(message = "가격은 0보다 커야합니다.")
        @NotNull(message = "가격은 필수입니다.")
        Integer price,
        @NotBlank(message = "URL을 입력해야 합니다.")
        String imageUrl){
}
