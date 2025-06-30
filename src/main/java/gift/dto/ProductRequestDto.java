package gift.dto;

import gift.entity.Product;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public record ProductRequestDto(
        @Length(max = 255) String name,
        @Min(0) Integer price,
        @Length(max = 255) String imageUrl) {
    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}

