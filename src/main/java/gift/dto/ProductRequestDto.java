package gift.dto;

public class ProductRequestDto {
    private String name;
    private int price;
    private String imageUrl;

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }

    // 기본 생성자 필수 (JSON 역직렬화용)
    public ProductRequestDto() {}
}
