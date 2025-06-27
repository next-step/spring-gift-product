package gift.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductCreateRequestDto(
        @NotBlank(message = "상품 이름은 필수입니다.")
        String name,
        @NotNull(message = "상품 가격은 필수입니다.")
        @PositiveOrZero(message = "상품 가격은 0 이상이어야 합니다.")
        Long price,
        String imageUrl
) {
}
