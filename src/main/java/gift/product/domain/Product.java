package gift.product.domain;

public record Product(
    Long id,
    String name,
    Integer price,
    String description,
    String imageUrl
) {

  public Product{
    validateName(name);
    validatePrice(price);
    validateDescription(description);
    validateImageUrl(imageUrl);
  }

  public static Product withId(Long id, String name, Integer price, String description, String imageUrl){
    validateId(id);
    return new Product(id, name, price,description,imageUrl);
  }
  public static Product withId(Long id, Product product){
    validateId(id);
    return new Product(id, product.name, product.price, product.description, product.imageUrl);
  }

  public static Product of(String name, Integer price, String description, String imageUrl){
    return new Product(null, name, price,description,imageUrl);
  }

  private static void validateId(Long id){
    if(id==null){
      throw new IllegalArgumentException("Id는 null일 수 없습니다.");
    }
    if(id<0){
      throw new IllegalArgumentException("Id는 음수일 수 없습니다");
    }
  }

  private static void validateName(String name) {
    if(name==null){
      throw new IllegalArgumentException("상품명은 null일 수 없습니다.");
    }
    if(name.isEmpty()){
      throw new IllegalArgumentException("상품명은 빈 값일 수 없습니다");
    }
  }

  private static void validatePrice(Integer price) {
    if(price ==null){
      throw new IllegalArgumentException("상품 가격은 null일 수 없습니다.");
    }
    if(price <0){
      throw new IllegalArgumentException("상품 가격은 음수일 수 없습니다");
    }
  }

  private static void validateDescription(String description) {
    if(description==null){
      throw new IllegalArgumentException("상품 설명은 null일 수 없습니다.");
    }
    if(description.isEmpty()){
      throw new IllegalArgumentException("상품 설명은 빈 값일 수 없습니다");
    }
  }

  private static void validateImageUrl(String imageUrl) {
    if(imageUrl==null){
      throw new IllegalArgumentException("이미지 URL은 null일 수 없습니다.");
    }
    if(imageUrl.isEmpty()){
      throw new IllegalArgumentException("이미지 URL은 빈 값일 수 없습니다");
    }
  }

}
