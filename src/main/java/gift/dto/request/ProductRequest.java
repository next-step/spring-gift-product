package gift.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank String name,
        @Positive int price,
        @NotBlank String imageUrl
) {}

