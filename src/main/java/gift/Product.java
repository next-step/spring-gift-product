package gift;

public class Product {

    private Long id;
    private final String name;
    private final Long price;

    public Product(String name, Long price) {
        this.id = null;
        this.name = name;
        this.price = price;
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

    public void setId(Long id) {
        this.id = id;
    }
}
