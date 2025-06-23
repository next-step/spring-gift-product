package gift.domain;


public class Product {
  private Long id;
  private String name;
  private int price;
  private String imageUrl;

  public Product(){
    this.id = null;
    this.name = null;
    this.price = 0;
    this.imageUrl = null;
  }

  public Product(Long id, String name, int price, String imageUrl){
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Long getId(){
    return this.id;
  }

  public String getName(){
    return this.name;
  }

  public int getPrice(){
    return this.price;
  }

  public String getImageUrl(){
    return this.imageUrl;
  }
}
