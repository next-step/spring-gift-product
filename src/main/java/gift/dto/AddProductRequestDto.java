package gift.dto;

public class AddProductRequestDto {
    
    private String name; //상품의 이름
    private Long price; //상품의 가격
    private String imageUrl; //상품의 이미지 URL
    
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
    
    //html form의 내용을 binding 하기 위해서는 setter의 존재 필수
    //즉 불변객체가 아닌 가변 객체여야 함
    
    //유효성 검사 묶기
    public Boolean isNotValid() {
        return (name == null || price == null || imageUrl == null);
    }
}
