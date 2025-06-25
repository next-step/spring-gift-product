package gift.dto;

public class ProductRequestDTO {
    private final Integer id;
    private final String name;
    private final Integer price;
    private final String imageUrl;

    public ProductRequestDTO(Integer id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
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
