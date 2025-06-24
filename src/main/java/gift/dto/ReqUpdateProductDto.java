package gift.dto;

import gift.entity.Product;

public class ReqUpdateProductDto {
    private String name;
    private Long price;
    private String imageUrl;

    public ReqUpdateProductDto() { }

    public void setName(String name) { this.name = name; }
    public void setPrice(Long price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Product toEntity(Long productId) {
        return new Product(productId, name, price, imageUrl);
    }
}
