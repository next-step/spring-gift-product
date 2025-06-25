package gift.dto;

import gift.entity.Product;

public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Long getPrice() {
        return this.price;
    }
    public String getImageUrl() {return this.imageUrl; }

    public ProductResponseDto(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductResponseDto(Product product) {
        this.id = product.id();
        this.name = product.name();
        this.price = product.price();
        this.imageUrl = product.imageUrl();
    }
}
