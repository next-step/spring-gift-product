package gift.domain.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
    @NotBlank(message = "상품 이름은 비어 있을 수 없습니다.")
    private String name;

    @NotNull(message = "상품 가격은 비어 있을 수 없습니다.")
    @Min(value = 1, message = "상품 가격은 1 이상의 값이어야 합니다.")
    private int price;

    @NotBlank(message = "이미지 URL은 비어 있을 수 없습니다.")
    private String imageUrl;

    public ProductRequest() {
    }
    
    public ProductRequest(String name, int price, String imageUrl) {
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
}
