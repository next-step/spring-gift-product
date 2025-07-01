package gift.dto;

public record ProductRequestDto(
        String name,
        Integer price,
        String imageUrl
) {

}
