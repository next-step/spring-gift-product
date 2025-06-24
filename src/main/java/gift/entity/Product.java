package gift.entity;

public class Product {
    private Long id;

    public Long getId() {
        return id;
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

    private String name;
    private int price;
    private String imageUrl;
}
