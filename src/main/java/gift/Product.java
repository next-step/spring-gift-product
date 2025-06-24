package gift;

public class Product {

    private Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public Product(String name, Long price, String imageUrl) {
        this.id = null;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Product(String name, Long price) {
        this(name, price, null);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
