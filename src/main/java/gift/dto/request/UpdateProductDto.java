package gift.dto.request;

import gift.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateProductDto(
        @NotBlank(message = "Product name is required") String name,
        @Min(value = 0, message = "Product price must be positive") Long price,
        String imageUrl
) {
    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}
