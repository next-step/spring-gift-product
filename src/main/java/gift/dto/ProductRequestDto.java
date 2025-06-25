package gift.dto;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
        @NotNull(message = "이름은 필수입니다.") String name,
        @NotNull(message = "가격은 필수입니다.") Long price
) {}
