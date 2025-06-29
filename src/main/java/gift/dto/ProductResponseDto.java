package gift.dto;

import gift.entity.Product;

public class ProductResponseDto {

    private Long id;
    private String name;
    private long price;
    private String imageUrl;

    public ProductResponseDto(Long id, String name, long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
