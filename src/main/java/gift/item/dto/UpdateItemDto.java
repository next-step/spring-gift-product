package gift.item.dto;

public class UpdateItemDto {

    private String name;
    private Integer price;
    private String imageUrl;

    protected UpdateItemDto() {}

    public UpdateItemDto(String name, Integer price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

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
