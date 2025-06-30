package gift.product.dto;

import gift.product.domain.Product;

public class ProductDto {
    private String name;
    private int price;
    private String imageUrl;

    public ProductDto() {}

    public ProductDto(String name, int price, String imageUrl) {
        if(price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDto(Product product) {
        if(product.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
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
