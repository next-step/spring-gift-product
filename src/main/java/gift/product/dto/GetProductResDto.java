package gift.product.dto;

import gift.product.domain.Product;
import lombok.Getter;

@Getter
public class GetProductResDto {
    private Long id;
    private String name;
    private String description;

    public GetProductResDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }
}
