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

  public void setName(String name) {     // ✅ 추가
    this.name = name;
  }

  public void setPrice(int price) {      // ✅ 추가
    this.price = price;
  }

  public void setImageUrl(String imageUrl) {  // ✅ 추가
    this.imageUrl = imageUrl;
  }

}
