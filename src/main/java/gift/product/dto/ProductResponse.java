package gift.product.dto;

import gift.domain.Product;


import java.util.UUID;

public class ProductResponse {

    private UUID id;
    private String id;
    private String name;
    private int price;
    private String imageURL;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageURL = product.getImageURL();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }
}
