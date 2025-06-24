package gift.dto;

import gift.Product;

public record ProductDto(Long id, String name, Long price, String imageUrl) {

    public static ProductDto from(Product p) {
        return new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getImageUrl());
    }
}
