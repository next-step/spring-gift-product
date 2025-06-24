package gift.dto;

public class ProductRequestDto {
    private Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public ProductRequestDto() {}


    // Getter
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
