package gift.product.domain;

import gift.product.dto.UpdateProductReqDto;
import lombok.Getter;

@Getter
public class Product {
    private final Long id;
    private String name;
    private int price;
    private String description;

    public Product(Long id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void updateProduct(UpdateProductReqDto dto) {
        this.name = dto.name();
        this.price = dto.price();
        this.description = dto.description();
    }
}
