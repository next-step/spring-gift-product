package gift.dto.response;

import gift.entity.Product;

public record ProductDto(Long id, String name, Long price, String imageUrl) {

    public static ProductDto from(Product p) {
        return new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getImageUrl());
    }
}
