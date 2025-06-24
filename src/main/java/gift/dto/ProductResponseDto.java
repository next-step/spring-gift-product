package gift.dto;

import gift.entity.Product;

public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Long getPrice() {
        return this.price;
    }

    public ProductResponseDto(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductResponseDto(Product product) {
        this.id = product.id();
        this.name = product.name();
        this.price = product.price();
    }
}
