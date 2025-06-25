package gift

public class ProductDTO {
    private String name;
    private int price;
    private String imageUrl;

    public ProductDTO(String name, int price, String imageUrl) {
        if(price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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
