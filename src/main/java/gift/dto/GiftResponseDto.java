package gift.dto;

import gift.entity.Gift;

public record GiftResponseDto(
    Long id,
    String name,
    int price,
    String imageUrl
) {

  public GiftResponseDto(Gift gift) {
    this(gift.getId(), gift.getName(), gift.getPrice(), gift.getImageUrl());
  }
}
