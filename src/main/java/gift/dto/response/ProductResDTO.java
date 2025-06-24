package gift.dto.response;

import gift.domain.Product;

public record ProductResDTO(Long id, String name, int price, String imageURL) {

    public static ProductResDTO toDTO(Product product) {
        return new ProductResDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageURL()
        );
    }
}
