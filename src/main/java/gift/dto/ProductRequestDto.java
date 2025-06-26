package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductRequestDto {

    @NotBlank(message = "상품 이름은 비어 있을 수 없습니다.")
    private String name;

    @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    private int price;

    @NotBlank(message = "이미지 URL은 비어 있을 수 없습니다.")
    private String imageUrl;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public void setName(String name) { this.name = name; }

    public void setPrice(int price) { this.price = price; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
