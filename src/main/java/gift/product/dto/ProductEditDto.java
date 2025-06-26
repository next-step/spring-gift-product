package gift.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductEditDto(
        @NotBlank(message = "상품명은 필수입니다.")
        String name,

        @NotNull(message = "가격은 필수입니다.")
        @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
        int price,

        String imageUrl
){
}
