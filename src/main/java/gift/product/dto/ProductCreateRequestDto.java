package gift.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductCreateRequestDto(
        @NotBlank String name,
        @NotNull @PositiveOrZero Long price,
        String imageUrl
) {
}
