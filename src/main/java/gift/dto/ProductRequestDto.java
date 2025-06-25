package gift.dto;

import gift.entity.Product;

public record ProductRequestDto(String name, Long price, String imageUrl) {

    public Product toEntity() {
        return new Product(null, this.name, this.price, this.imageUrl);
    }

}
