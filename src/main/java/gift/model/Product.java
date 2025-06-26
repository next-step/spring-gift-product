package gift.model;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private String image;

    public Product(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = imageUrl;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getImage() { return image; }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
}