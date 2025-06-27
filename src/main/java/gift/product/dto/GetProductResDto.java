package gift.product.dto;

import gift.product.domain.Product;

public record GetProductResDto(
    Long id,
    String name,
    int price,
    String description
) {

  public static GetProductResDto from(Product product) {
    return new GetProductResDto(product.id(), product.name(), product.price(),
        product.description());
  }
}
