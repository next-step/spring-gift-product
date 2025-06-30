package gift.dto;

import java.math.BigInteger;

public record ProductRequestDTO (
        String name,
        BigInteger price,
        String imageUrl
) {}
