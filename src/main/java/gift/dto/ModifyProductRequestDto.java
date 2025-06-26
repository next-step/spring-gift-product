package gift.dto;

public class ModifyProductRequestDto {
    
    private String name; //상품의 이름
    private Long price; //상품의 가격
    private String imageUrl; //상품의 이미지 URL
    
    public ModifyProductRequestDto() {
    }
    
    public ModifyProductRequestDto(String name, Long price, String imageUrl) {
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
    
    public Boolean isNotValidForModify() {
        return (name == null || price == null || imageUrl == null);
    }
    
    public Boolean isNotValidForModifyInfo() {
        return (name == null && price == null && imageUrl == null);
    }
}
