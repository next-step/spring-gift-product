package gift.dto;

import gift.entity.Item;

public record ItemResponse(
    Long id,
    String name,
    int price,
    String imageUrl
) {

    public static ItemResponse from(Item item) {
        return new ItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
    }
}