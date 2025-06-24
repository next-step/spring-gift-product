package gift.product;

import gift.product.dto.ProductUpdateRequest;

public class Product {
    private Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public static Long nextId = 1L;

    public Product(String name, Long price, String imageUrl) {
        this.id = nextId;
        nextId = nextId + 1;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void update(ProductUpdateRequest req){
        this.name = req.name();
        this.price = req.price();
        this.imageUrl = req.imageUrl();
    }
}
