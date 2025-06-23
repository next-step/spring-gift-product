package gift.dto;

import gift.Product;

public record ProductDto(Long id, String name, Long price) {

    public static ProductDto from(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
}
