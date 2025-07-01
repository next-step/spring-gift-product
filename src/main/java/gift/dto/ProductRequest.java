// src/main/java/gift/dto/ProductRequest.java
package gift.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank(message = "상품 이름은 필수 입력값입니다.") String name,
        @Min(value = 1, message = "가격은 1원 이상이어야 합니다.") int price,
        @NotBlank(message = "이미지 URL은 필수 입력값입니다.") String imageUrl
) {

}
