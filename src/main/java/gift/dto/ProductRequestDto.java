package gift.dto;

public class ProductRequestDto {

    private String name;
    private Long price;
    private String imageUrl;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, Long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
