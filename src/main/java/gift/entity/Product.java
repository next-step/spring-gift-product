package gift.entity;


import gift.dto.response.ProductResponseDto;

public record Product(long productId, String name, int price, String imageURL) {

  public ProductResponseDto toResponseDto() {
    return new ProductResponseDto(productId, name, price, imageURL);
  }
}