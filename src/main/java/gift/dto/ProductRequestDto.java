package gift.dto;

public class ProductRequestDto {

    private String name;
    private Integer price;
    private String imageUrl;

    public void setName(String name) { this.name = name; }

    public void setPrice(Integer price) { this.price = price; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl;}

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
