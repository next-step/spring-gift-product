package gift.entity;

public class Product {

    private Long id;
    private String name;
    private long price;
    private String imageUrl;

    public Product(String name, long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void changeName(String name) {
        if(name != null)
            this.name = name;
    }

    public void changePrice(Long price) {
        if(price != null)
            this.price = price;
    }

    public void changImageUrl(String imageUrl) {
        if(imageUrl != null)
            this.imageUrl = imageUrl;
    }

    public Long getId() {
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
}
