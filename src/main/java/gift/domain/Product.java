package gift.domain;

public class Product {

  private Long id;
  private String name;
  private Integer price;
  private String imageUrl;

  public Product(Long id, String name, Integer price, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Product(String name, Integer price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public void update(String name, Integer price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Integer getPrice() {
    return this.price;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
