package gift.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductReqDto (
        @NotBlank(message = "상품명은 필수값입니다")
        String name,
        @Positive(message = "가격은 1원 이상이어야 합니다")
        @NotNull(message = "가격은 필수값입니다")
        Integer price,
        @Size(max = 500, message = "상품설명은 최대 500자까지 입력할 수 있습니다")
        @NotBlank(message = "상품 설명은 필수값입니다")
        String description
){}
