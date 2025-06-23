package gift.product.dto;

import gift.domain.Product;

public class ProductResponse {

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

    public String getId() {
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
