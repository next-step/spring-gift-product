package gift.dto;

public class CreateProductRequestDto {

    private String name;
    private Long price;
    private String imageUrl;

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
