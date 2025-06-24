package gift.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequestDto(
        @NotBlank String name,
        @NotNull Long price,
        String imageUrl
) {
}
