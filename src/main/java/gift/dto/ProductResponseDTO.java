package gift.dto;

import java.math.BigInteger;

public record ProductResponseDTO (
        Integer id,
        String name,
        BigInteger price,
        String imageUrl
) {}
