package gift.entity;

public class Product {
    
    private Long id;
    private String name;
    private Long price;
    private String imageURL;
    
    //생성자
    public Product(Long id, String name, Long price, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
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
    
    public String getImageURL() {
        return imageURL;
    }
}
