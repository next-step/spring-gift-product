package gift.dto;

public class ProductRequestDto {
    // private Long id; auto_increment
    private String name;
    private Integer price;
    private String imageUrl;

    //setter
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }


    //getter
    public String getName(){
        return name;
    }

    public Integer getPrice(){
        return price;
    }

    public String getImageUrl(){
        return imageUrl;
    }

}
