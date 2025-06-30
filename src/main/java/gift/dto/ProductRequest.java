// src/main/java/gift/dto/ProductRequest.java
package gift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductRequest {

    @NotBlank(message = "상품 이름은 필수 입력값입니다.")
    private String name;

    @Min(value = 1, message = "가격은 1원 이상이어야 합니다.")
    private int price;

    @NotBlank(message = "이미지 URL은 필수 입력값입니다.")
    private String imageUrl;

    public ProductRequest() {
    }

    // getters / setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
