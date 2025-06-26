package gift.dto;

public class ProductRequestDTO {
    private final String name;
    private final Integer price;
    private final String imageUrl;

    public ProductRequestDTO(String name, Integer price, String imageUrl) {
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
