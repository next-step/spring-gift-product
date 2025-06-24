package gift.dto;

import gift.entity.Product;

public record ProductAddResponseDto (Long id, String name, Long price, String url) {

    public ProductAddResponseDto(Product product) {
        this(product.id(), product.name(), product.price(), product.url());
    }
}
