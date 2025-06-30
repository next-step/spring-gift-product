package gift;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {}

    public Product(Long id, String name, int price, String imageUrl) {
        if(price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, ProductDTO productdto) {
        if(productdto.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.id = id;
        this.name = productdto.getName();
        this.price = productdto.getPrice();
        this.imageUrl = productdto.getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }
}