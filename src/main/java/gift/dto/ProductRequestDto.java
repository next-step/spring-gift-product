package gift.dto;

import jakarta.validation.constraints.Min;

public class ProductRequestDto {

    private String name;
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private Long price;
    private String imageUrl;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, Long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
