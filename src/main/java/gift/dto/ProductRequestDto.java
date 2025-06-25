package gift.dto;

import gift.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto (
        @NotNull String name,
        @NotNull @Min(1) Integer price,
        @NotNull String imageUrl
) {
    public static ProductRequestDto empty() {
        return new ProductRequestDto("", 1, "");
    }
    public static ProductRequestDto from(Product product) {
        return new ProductRequestDto(
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}