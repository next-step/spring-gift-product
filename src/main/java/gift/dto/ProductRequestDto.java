package gift.dto;

import gift.exception.InvalidImageUrlException;
import gift.exception.InvalidNameException;
import gift.exception.InvalidPriceException;

public record ProductRequestDto(
    String name,
    int price,
    String imageUrl
) {

  public ProductRequestDto {
    if (name == null || name.isBlank()) {
      throw new InvalidNameException();
    }
    if (price < 0) {
      throw new InvalidPriceException();
    }
    if (imageUrl == null || imageUrl.isBlank()) {
      throw new InvalidImageUrlException();
    }
  }
}
