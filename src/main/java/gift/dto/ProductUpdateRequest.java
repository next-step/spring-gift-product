package gift.dto;

import gift.entity.Product;

public record ProductUpdateRequest (
    String name,
    Long price,
    String imageUrl
) {
    public ProductUpdateRequest(String name, Long price, String imageUrl) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("상품 이름은 필수입니다.");
        }
        this.name = name;
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }
        this.price = price;
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("상품 이미지 URL은 필수입니다.");
        }
        this.imageUrl = imageUrl;
    }

    public Product toEntity(Long productId) {
        return new Product(productId, name, price, imageUrl);
    }
}
