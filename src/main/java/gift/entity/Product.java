package gift.entity;

import gift.dto.ProductRequestDTO;

public class Product {
    private Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateFromProductRequestDTO(ProductRequestDTO dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.imageUrl = dto.getImageUrl();
    }
}
