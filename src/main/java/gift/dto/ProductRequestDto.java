package gift.dto;

import gift.entity.Product;

public record ProductRequestDto(String name, Long price, String imageUrl) { }
