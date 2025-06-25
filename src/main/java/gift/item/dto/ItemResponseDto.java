package gift.item.dto;

public record ItemResponseDto(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {

}
