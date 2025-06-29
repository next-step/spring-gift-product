package gift.dto;

import gift.entity.Item;
import jakarta.validation.constraints.Min;

public record ItemUpdateDTO(Long id, String name, @Min(0) Integer price, String imageUrl) {
    public ItemUpdateDTO(Item item) {
        this(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
    }
}
