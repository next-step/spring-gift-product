package gift.entity;

import java.math.BigInteger;

public class Product {
    private Integer id;
    private String name;
    private BigInteger price;
    private String imageUrl;

    public Product() {}

    public Product(Integer id, String name, BigInteger price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigInteger getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void update(String name, BigInteger price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
