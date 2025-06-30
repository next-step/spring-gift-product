// src/main/java/gift/dto/ProductResponse.java
package gift.dto;

public record ProductResponse(
        Long id,
        String name,
        int price,
        String imageUrl
) {

    public ProductResponse {
    }
}
