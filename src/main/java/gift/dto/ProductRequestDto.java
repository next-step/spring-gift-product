package gift.dto;

import gift.model.Product;

public record ProductRequestDto(String name, int price, String imageUrl) {
    public boolean isValid() {
        return this.name != null && this.price > 0;
    }

    public Product toEntity() {
        return new Product(null, this.name, this.price, this.imageUrl);
    }
}
