package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(

    @NotBlank(message = "상품명은 필수입니다.")
    String name,
    @NotNull(message = "가격은 필수입니다.")
    Integer price,
    @NotBlank(message = "이미지 URL은 필수입니다.")
    String imageUrl

) {

}
