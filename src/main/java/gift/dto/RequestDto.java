package gift.dto;

public class RequestDto {
    private final String name;
    private final Double price;
    private final String imageUrl;

    RequestDto(String name, Double price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
