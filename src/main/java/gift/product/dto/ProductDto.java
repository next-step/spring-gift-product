package gift.product.dto;

public class ProductDto {
    private String name;
    private int price;
    private String imageUrl;

    public ProductDto() {}

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
