package gift.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
    Long id;
    String name;
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    int price;
    String imageUrl;
}
