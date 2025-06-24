package gift.dto;

import gift.domain.Product;

public record ReadProductResponse(Long id, String name, Integer price, String imageUrl) {

    public static ReadProductResponse of(Product product) {
        return new ReadProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );
    }
}
