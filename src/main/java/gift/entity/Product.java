package gift.entity;

public record Product(
        Long id,
        String name,
        int price,
        String imageUrl
) {}
