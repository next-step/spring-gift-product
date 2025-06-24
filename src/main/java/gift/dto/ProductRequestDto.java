package gift.dto;

public class ProductRequestDto {
    // private Long id; auto_increment
    private String name;
    private Integer price;
    private String imageUrl;

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
