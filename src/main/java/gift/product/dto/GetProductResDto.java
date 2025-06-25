package gift.product.dto;

import gift.product.domain.Product;
import lombok.Getter;

public record GetProductResDto (
    Long id,
    String name,
    String description
) {

    public static GetProductResDto from(Product product) {
        return new GetProductResDto(product.getId(), product.getName(), product.getDescription());
    }
}
