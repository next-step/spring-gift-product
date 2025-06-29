package gift.dto;

public record ProductRequestDto(
    String name,
    Integer price,
    String imageUrl
) {
    public static ProductRequestDto from() {
        return new ProductRequestDto(
            "",
            0,
            ""
        );
    }
}
