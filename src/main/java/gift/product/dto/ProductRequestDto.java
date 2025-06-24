package gift.product.dto;

import gift.product.entity.Product;

public class ProductRequestDto {

    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public static ProductRequestDto fromEntity(Product product) {

        ProductRequestDto dto = new ProductRequestDto();

        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        dto.imageUrl = product.getImageUrl();

        return dto;
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
