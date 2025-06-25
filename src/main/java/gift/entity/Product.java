package gift.entity;

import gift.dto.ProductRequestDto;

public class Product {

  private Long id;
  private String name;
  private Long price;
  private String imageUrl;

  public Product(Long id, String name, Long price, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Product(ProductRequestDto requestDto) {
    this.id = requestDto.getId();
    this.name = requestDto.getName();
    this.price = requestDto.getPrice();
    this.imageUrl = requestDto.getImageUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Long getPrice() {
    return price;
  }

  public String getImageUrl() {
    return imageUrl;
  }


}
