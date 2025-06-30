package gift.domain;

public class Product {

    private final Long id;
    private final String name;
    private final Integer price;
    private final String imageUrl;

    private Product(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static Product of(Long id, String name, Integer price, String imageUrl) {
        return new Product(id, name, price, imageUrl);
    }

    public Product createUpdatedProduct(String name, Integer price, String imageUrl) {
        return new Product(this.id, name, price, imageUrl);
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

    public String getImageUrl() {
        return imageUrl;
    }
}
