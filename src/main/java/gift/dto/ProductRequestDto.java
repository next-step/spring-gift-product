package gift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto (
        @NotNull String name,
        @NotNull @Min(1) Integer price,
        @NotNull String imageUrl
) {}