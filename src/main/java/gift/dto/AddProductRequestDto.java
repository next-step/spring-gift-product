package gift.dto;

public class AddProductRequestDto {
    
    private final String name; //상품의 이름
    private final Long price; //상품의 가격
    private final String imageUrl; //상품의 이미지 URL
    
    public AddProductRequestDto(String name, Long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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
