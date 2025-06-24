package gift.domain;

public class Product {

    static Long id; // auto Increment를 구현하기 위한 static 필드
    String name;
    int price;
    String imageURL;

    static {
        id = 0L;
    }

    public Product(String name, int price, String imageURL) {
        ++id;
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
}
