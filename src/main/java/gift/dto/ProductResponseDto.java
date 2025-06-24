package gift.dto;

import gift.entity.Product;

public record ProductResponseDto (Long id, String name, Long price, String url){
    public ProductResponseDto(Product product) {
        this(product.id(), product.name(), product.price(), product.url());
    }
}
