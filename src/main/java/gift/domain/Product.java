package gift.domain;

public class Product {

    Long id;
    String name;
    Integer price;
    String imageURL;

    public Product(String name, Integer price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public Product(Long id, String name, Integer price, String imageURL) {
        this.id = id;
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

    public Integer getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
