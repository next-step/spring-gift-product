package gift.dto;

public record ItemRequest(
    Long id,
    String name,
    int price,
    String imageUrl
) {

}