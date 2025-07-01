package gift.dto;

import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record ProductRequestDTO (
        @Size(max = 255, message = "상품명은 255자를 초과할 수 없습니다.")
        String name,
        BigInteger price,
        @Size(max = 1000, message = "이미지 URL은 1000자를 초과할 수 없습니다.")
        String imageUrl
) {}
