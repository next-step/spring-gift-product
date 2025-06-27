package gift.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "price", "imageUrl"})
public class Product {

    private long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {}

    public Product(String name, int price, String imageUrl) {
        validate(name, price, imageUrl);

        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    private void validate(String name, int price, String imageUrl) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }

        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("이미지 URL은 필수입니다.");
        }

        try {
            new java.net.URL(imageUrl).toURI();
        } catch (Exception e) {
            throw new IllegalArgumentException("유효한 이미지 URL이 아닙니다.");
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
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

}
