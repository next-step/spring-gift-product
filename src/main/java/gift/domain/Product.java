package gift.domain;

public class Product {

    Long id;
    String name;
    int price;
    String imageURL;

    public Product(String name, int price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void update(String name, Integer price, String imageURL) {
        if (name != null) {
            this.name = name;
        }
        if (price != null) {
            this.price = price;
        }
        if (imageURL != null) {
            this.imageURL = imageURL;
        }
    }
}
