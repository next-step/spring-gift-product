package gift.domain;

public class Product {

    static Long autoIncrement; // auto Increment를 구현하기 위한 static 필드
    Long id;
    String name;
    int price;
    String imageURL;

    static {
        autoIncrement = 0L;
    }

    public Product(String name, int price, String imageURL) {
        this.id = ++autoIncrement;
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

    public Product update(String name, Integer price, String imageURL) {
        if (name != null) {
            this.name = name;
        }
        if (price != null) {
            this.price = price;
        }
        if (imageURL != null) {
            this.imageURL = imageURL;
        }

        return this;
    }
}
