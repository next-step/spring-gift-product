package gift.model;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String image;

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = imageUrl;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImage() { return image; }
}