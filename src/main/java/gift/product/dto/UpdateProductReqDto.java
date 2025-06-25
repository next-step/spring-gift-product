package gift.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductReqDto (
        String name,
        @Min(value = 0, message = "가격은 0원 이상이어야 합니다")
        Integer price,
        @Size(max = 500, message = "상품설명은 최대 500자까지 입력할 수 있습니다")
        String description
){}
