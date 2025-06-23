package gift.dto;

import gift.entity.Product;

public class ProductResponse {
    private final Long id;
    private final String name;
    private final Integer price;
    private final String imageUrl;

    private ProductResponse(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageURL()
        );
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getImageURL() { return imageUrl; }
}
