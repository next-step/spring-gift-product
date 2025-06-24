package gift.dto;

import gift.entity.Product;

public class ResponseDto {
    private final Long id;
    private final String name;
    private final Double price;
    private final String imageUrl;

    public ResponseDto(Product product) {
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

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
