package gift.dto;

public class RequestDto {
    private String name;
    private Double price;
    private String imageUrl;

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
