package gift.dto;

import gift.entity.Product;

public class ProductRequestDto {
    private String name;
    private Long price;
    private String imageUrl;

    public ProductRequestDto() {}

    public ProductRequestDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
