package gift.dto.response;

import gift.domain.Product;

public record ProductSaveResDTO(Long id, String name, int price, String imageURL) {

    public static ProductSaveResDTO toDTO(Product product) {
        return new ProductSaveResDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageURL()
        );
    }
}
