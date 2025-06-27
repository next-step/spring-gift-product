package gift.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateProductReqDto(
    @NotBlank(message = "상품명은 필수값입니다")
    String name,

    @NotNull(message = "가격은 필수값입니다")
    @PositiveOrZero(message = "가격은 0원 이상이어야 합니다")
    Integer price,

    @NotBlank(message = "상품설명은 필수값입니다")
    @Size(max = 500, message = "상품설명은 최대 500자까지 입력할 수 있습니다")
    String description
) {

}
