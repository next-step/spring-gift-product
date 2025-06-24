package gift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProductRequestDto {

    @NotBlank(message = "상품 이름은 비어 있을 수 없습니다.")
    private final String name;

    @NotNull(message = "가격은 필수 입력입니다.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private final Long price;

    private final String imageUrl;

    public CreateProductRequestDto(String name, Long price, String imageUrl) {
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
