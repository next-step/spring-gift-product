package gift.entity;

public class Product {
    private Long id;
    private String name;
    private Long price;
    private String imageURL;

    public Product(){}
    public Product(Long id, String name, Long price, String imageURL){
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Long getPrice(){
        return price;
    }
    public void setPrice(Long price){
        this.price = price;
    }
    public String getImageURL(){
        return imageURL;
    }
    public void setImageURL(){
        this.imageURL = imageURL;
    }

}
