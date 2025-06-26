package gift.product.dto;

import jakarta.validation.constraints.*;

public record UpdateProductReqDto (
        @NotBlank(message = "상품명은 필수값입니다")
        String name,
        @PositiveOrZero(message = "가격은 0원 이상이어야 합니다")
        @NotNull(message = "가격은 필수값입니다")
        Integer price,
        @Size(max = 500, message = "상품설명은 최대 500자까지 입력할 수 있습니다")
        @NotBlank(message = "상품 설명은 필수값입니다")
        String description
){}
