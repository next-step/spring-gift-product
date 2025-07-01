package gift.product.dto;

import gift.product.domain.Product;
import jakarta.validation.constraints.*;

public record ProductRequestDto(
        @NotBlank(message = "상품명은 필수입니다.")
        @Size(max = 100, message = "상품명은 100자 이내여야 합니다.")
        String name,

        @NotNull(message = "가격은 필수입니다.")
        @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
        @Max(value = 200_000_000, message = "가격은 20억 이하이어야 합니다.")
        int price,

        @NotBlank(message = "이미지 URL은 필수입니다.")
        @Size(max = 2000, message = "이미지 URL은 2000자 이내여야 합니다.")
        String imageUrl
){
    private static final String DEFAULT_NAME = "Default";
    private static final int DEFAULT_PRICE = 0;
    private static final String DEFAULT_IMAGE_URL = "";

    public static ProductRequestDto getEmpty() {
        return new ProductRequestDto(DEFAULT_NAME, DEFAULT_PRICE, DEFAULT_IMAGE_URL);
    }

    public Product toProduct() {
        return new Product(name, price, imageUrl);
    }
}
