package gift.dto;

public class PatchProductRequestDto {
    private final String name;
    private final Long price;
    private final String imageUrl;

    public PatchProductRequestDto(String name, Long price, String imageUrl) {
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
