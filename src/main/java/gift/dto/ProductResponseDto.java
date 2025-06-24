package gift.dto;

import gift.entity.Product;

public class ProductResponseDto {
    private final Long id;
    private final String name;
    private final Long price;
    private final String imageUrl;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
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
