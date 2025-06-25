package gift.product.domain;

import lombok.Getter;

@Getter
public class Product {
    private Long id;
    private String name;
    private int price;
    private String description;

    public Product(Long id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
