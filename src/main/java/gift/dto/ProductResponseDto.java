package gift.dto;

import gift.entity.Product;

public class ProductResponseDto {
    Long id;
    String name;
    int price;
    String imageUrl;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }
}
