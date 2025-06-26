package gift.entity;

import gift.dto.RequestDto;

public class Product {

    private Long productId;
    private String name;
    private Double price;
    private String imageUrl;

    public Product(String name, Double price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(Long productId, String name, Double price, String imageUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void update(RequestDto requestDto) {
        this.name = requestDto.name();
        this.price = requestDto.price();
        this.imageUrl = requestDto.imageUrl();
    }
}