package gift.item.dto;

public record ItemUpdateDto(
    String name,
    Integer price,
    String imageUrl
) {
    
}
