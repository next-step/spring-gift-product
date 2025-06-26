package gift.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ProductRequestDto (
    String name,
    @Min(value = 0)
    @Max(value = 1000000000000L)
    long price,
    String imageUrl
) {
}
