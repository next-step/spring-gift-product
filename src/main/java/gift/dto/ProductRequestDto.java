package gift.dto;

import jakarta.validation.constraints.Min;

public record ProductRequestDto(
        String name,
        @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
        Long price,
        String imageUrl
) {

}
