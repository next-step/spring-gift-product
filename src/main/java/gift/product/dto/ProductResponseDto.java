package gift.product.dto;

import gift.product.entity.Product;

public class ProductResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    private ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    public static ProductResponseDto fromEntity(Product product) {
        return new ProductResponseDto(product);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
