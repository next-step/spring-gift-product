package gift.domain;

import java.util.UUID;

public class Product {

    private String id;
    private String name;
    private int price;
    private String imageURL;

    protected Product() {}

    public Product(String name, int price, String imageURL) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getId() {
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
}
