package gift.dto;

public class ProductRequestDto{
    private String name;
    private Integer price;
    private String imageUrl;

    public String getName(){
        return this.name;
    }

    public Integer getPrice(){
        return this.price;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
}