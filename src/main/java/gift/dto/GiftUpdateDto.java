// src/main/java/gift/dto/GiftUpdateDto.java
package gift.dto;

public class GiftUpdateDto {

  private String name;
  private Integer price;
  private String imageUrl;

  public GiftUpdateDto() {
  }

  public GiftUpdateDto(String name, Integer price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
