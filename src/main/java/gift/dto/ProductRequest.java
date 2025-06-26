package gift.dto;

public class ProductRequest {

    private String name;
    private int price;
    private String imageUrl;

    public ProductRequest() {
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
