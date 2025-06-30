package gift.entity;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;


public class Product {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public Product(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }

    public void updateWith(ProductRequest request) {
        this.name = request.name();
        this.price = request.price();
        this.imageUrl = request.imageUrl();
    }

    public ProductResponse toResponse() {
        return new ProductResponse(id, name, price, imageUrl);
    }
}
