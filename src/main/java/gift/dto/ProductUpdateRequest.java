package gift.dto;

import gift.entity.Product;

public class ProductUpdateRequest {
    private String name;
    private Long price;
    private String imageUrl;

    public ProductUpdateRequest() { }

    public void setName(String name) {
        if (name != null && name.isEmpty()) {
            throw new IllegalArgumentException("상품 이름은 비워둘 수 없습니다.");
        }
        this.name = name;
    }
    public void setPrice(Long price) {
        if (price != null && price <= 0) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }
        this.price = price;
    }
    public void setImageUrl(String imageUrl) {
        if (imageUrl != null && imageUrl.isEmpty()) {
            throw new IllegalArgumentException("상품 이미지 URL은 비워둘 수 없습니다.");
        }
        this.imageUrl = imageUrl;
    }

    public Product toEntity(Long productId) {
        return new Product(productId, name, price, imageUrl);
    }
}
