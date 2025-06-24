package gift.product.dto;

public class ProductUpdateRequest {
    private String name;
    private int price;
    private String imageURL;

    protected ProductUpdateRequest() {}

    public ProductUpdateRequest(String name, int price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }
}
