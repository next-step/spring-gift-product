package gift.dto;

import gift.entity.Item;
import jakarta.validation.constraints.Min;

public record ItemCreateDTO(String name, @Min(0) Integer price, String imageUrl) {


    public ItemCreateDTO(Item saveditem) {
        this(saveditem.getName(), saveditem.getPrice(), saveditem.getImageUrl());
    }
}
