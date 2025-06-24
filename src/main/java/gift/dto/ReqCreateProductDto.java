package gift.dto;

import gift.entity.Product;

public class ReqCreateProductDto {
    private String name;
    private Long price;
    private String imageUrl;

    public ReqCreateProductDto() { }

    public void setName(String name) { this.name = name; }
    public void setPrice(Long price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Product toProduct() {
        return new Product(null, name, price, imageUrl);
    }
}
