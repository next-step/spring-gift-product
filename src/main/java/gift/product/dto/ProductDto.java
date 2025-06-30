package gift.product.dto;

import gift.product.domain.Product;

public class ProductDto {
    private String name;
    private int price;
    private String imageUrl;

    public ProductDto() {}

    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
