package gift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateProductRequestDto {

    @NotBlank(message = "상품명을 입력하세요.")
    private String name;

    @NotNull(message = "가격을 입력하세요.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private Integer price;

    @NotBlank(message = "이미지 URL을 입력하세요.")
    private String imageUrl;

    public String getName()
    {
        return name;
    }
    public Integer getPrice() {
        return price;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
