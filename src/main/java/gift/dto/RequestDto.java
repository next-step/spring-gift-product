package gift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestDto(
    @NotNull(message = "Name must not be null.")
    String name,

    @NotNull(message = "Price must not be null.")
    @Min(value = 0, message = "Price must be greater than or equal to zero.")
    Double price,

    @NotNull(message = "Image URL must not be null.")
    String imageUrl) {

}