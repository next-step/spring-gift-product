package gift.dto.api;

public class ModifyProductRequestDto {
    
    private String name;
    private Long price;
    private String imageUrl;
    
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
