package gift.product.entity;

import gift.product.dto.ProductResponseDto;

public class Product {
    private final Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public Product(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public ProductResponseDto toResponseDto() {
        return new ProductResponseDto(id, name, price, imageUrl);
    }
}
