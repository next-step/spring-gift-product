package gift.entity;

import gift.dto.RequestDto;

public class Product {
    private final Long id;
    private String name;
    private Double price;
    private String imageUrl;


    public Product(Long id, String name,Double price, String imageUrl) {
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

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void update(RequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.imageUrl = requestDto.getImageUrl();
    }
}
