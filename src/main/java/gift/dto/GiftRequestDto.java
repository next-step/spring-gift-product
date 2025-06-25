package gift.dto;

public class GiftRequestDto {

  private String name;
  private int price;
  private String imageUrl;

  public GiftRequestDto() {
  }

  public GiftRequestDto(String name, int price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  void setName(String name) {
    this.name = name;
  }

  void setPrice(int price) {
    this.price = price;
  }

  void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
