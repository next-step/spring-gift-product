package gift.dto;

import gift.model.Product;

public class ProductDto {

  private Long id;
  private String name;
  private int price;
  private String imageUrl;

  public ProductDto() {
  }

  public ProductDto(Long id, String name, int price, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  // 도메인 객체를 DTO로 변환 (null 방지 포함)
  public static ProductDto from(Product product) {
    if (product == null) {
      return null;  // 또는 throw new IllegalArgumentException("null product");
    }
    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getImageUrl()
    );
  }

  // DTO를 도메인 객체로 변환
  public Product toEntity() {
    return new Product(id, name, price, imageUrl);
  }

  // getter & setter
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
