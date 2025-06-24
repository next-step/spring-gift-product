package gift.dto;

import gift.Entity.Product;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public ProductResponseDto(Product product) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}
