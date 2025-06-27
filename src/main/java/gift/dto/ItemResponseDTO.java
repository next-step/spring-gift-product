package gift.dto;

import gift.entity.Item;

public record ItemResponseDTO(Long id, String name, Integer price, String imageUrl) {

    public static ItemResponseDTO from(Item item) {
        return new ItemResponseDTO(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
    }
}
