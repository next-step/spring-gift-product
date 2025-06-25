package gift.dto;

import gift.Entity.Product;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    /*
    Postman에서 해본 결과 오류가 발생함
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
    이런 식으로 작성이 될 경우에
    자기 자신에 자기 자신을 대입하여 항상 null이 되는 현상이 발생합니다.
    따라서 아래와 같이 수정하여 줍니다.
     */

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}
