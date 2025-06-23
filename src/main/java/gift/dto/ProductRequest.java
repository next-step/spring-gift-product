package gift.dto;

import gift.entity.Product;

public class ProductRequest {
    private String name;
    private Integer price;
    private String imageURL;

    public ProductRequest() {
    }

    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getImageURL() { return imageURL; }

    public Product toEntity() {
        return new Product(this.name, this.price, this.imageURL);
    }
}
