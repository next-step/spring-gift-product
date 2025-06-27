package gift.dto.api;

public class AddProductRequestDto {
    
    private String name;
    private Long price;
    private String imageUrl;
    
    public AddProductRequestDto() {
    }
    
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
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(Long price) {
        this.price = price;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    //유효성 검사 묶기
    public Boolean isNotValid() {
        return (name == null || price == null || imageUrl == null);
    }
}
