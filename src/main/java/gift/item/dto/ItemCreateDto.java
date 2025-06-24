package gift.item.dto;

public class ItemCreateDto {

    private final String name;
    private final Integer price;
    private final String imageUrl;

    public ItemCreateDto(String name, Integer price, String imageUrl) {
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
