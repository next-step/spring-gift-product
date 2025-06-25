package gift.dto;

import jakarta.validation.constraints.Min;

public class ProductResponseDto {
    private Long id;
    private String name;
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private int price;
    private String imageUrl;

    public ProductResponseDto(Long id, String name, int price, String imageUrl) {
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

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
