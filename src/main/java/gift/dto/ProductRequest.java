package gift.dto;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.math.BigInteger;

public record ProductRequest(

        @NotBlank(message = "상품명은 필수입니다.")
        String name,
        @NotBlank(message = "가격은 필수입니다.")
        @Pattern(regexp = "\\d+", message = "가격은 숫자만 가능합니다.")
        String price,
        @NotBlank(message = "이미지 URL은 필수입니다.")
        @Pattern(
                regexp = "^(https?://).+",
                message = "이미지 URL은 http 또는 https로 시작해야 합니다."
        )
        String imageUrl
) {

    public Integer validatedPrice() {
        try {
            var parsedPrice = new BigInteger(price);
            if (parsedPrice.compareTo(BigInteger.ZERO) < 0 ||
                    parsedPrice.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                throw new BusinessException(ErrorCode.PRICE_OUT_OF_RANGE);
            }
            return parsedPrice.intValue();
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.INVALID_PRICE_FORMAT);
        }
    }
}
