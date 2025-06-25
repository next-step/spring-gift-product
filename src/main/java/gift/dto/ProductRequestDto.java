package gift.dto;

import jakarta.validation.constraints.Min;

public record ProductRequestDto (
    String name,
    @Min(value = 0)
    int price,
    String imageUrl
) {
}
