package gift.dto;


/*
 ProductRequest와 ProductResponse 현재 동일한 코드 구조를 가지고 있어 하나의 dto로 합쳐도 되지만,
 이후에 기능 확장, 필드 추가 등을 고려해 미리 분리해 놓는 게 낫다고 판단했습니다.
*/

public class ProductRequest {
  Long id;
  String name;
  int price;
  String imageUrl;

  public ProductRequest(Long id, String name, int price, String imageUrl){
    this.id = id;
    this.name=name;
    this.price = price;
    this.imageUrl=imageUrl;
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
