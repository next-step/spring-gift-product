package gift.dto;

import gift.domain.Product;

public record ProductResponseDto(Long id, String name, Integer price, String imageUrl) {

  public ProductResponseDto(Product product) {
    this(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getImageUrl()
    );
  }
}
