package gift.entity;

public class Product {
    
    private Long id; //상품의 id
    private String name; //상품의 이름
    private Long price; //상품의 가격
    private String imageUrl; //상품의 이미지 URL
    
    //생성자
    public Product(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    
    //Getter
    public Long getId() {
        return id;
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
    
    public void setId(Long id) {
        this.id = id;
    }
}
