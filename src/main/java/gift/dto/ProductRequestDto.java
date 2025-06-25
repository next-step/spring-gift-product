package gift.dto;

public class ProductRequestDto {

  private Long id;
  private String name;
  private Long price;
  private String imageUrl;

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
