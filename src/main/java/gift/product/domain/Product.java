package gift.product.domain;

import gift.product.dto.ProductDto;

public class Product {
    private String id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {}

    public Product(String id, String name, int price, String imageUrl) {
        if(price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(String id, ProductDto productdto) {
        if(productdto.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.id = id;
        this.name = productdto.getName();
        this.price = productdto.getPrice();
        this.imageUrl = productdto.getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}