package gift.product.entity;

import gift.product.dto.ProductCreateRequestDto;

public class Product {

    private Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public Product() {
    }

    public Product(Long id, ProductCreateRequestDto dto) {
        this.id = id;
        this.name = dto.name();
        this.price = dto.price();
        this.imageUrl = dto.imageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
