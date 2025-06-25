package gift.item.dto;

public record ItemCreateDto(
    String name,
    Integer price,
    String imageUrl
) {

}
