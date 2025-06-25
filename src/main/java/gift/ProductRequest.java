package gift;

public record ProductRequest(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {

}