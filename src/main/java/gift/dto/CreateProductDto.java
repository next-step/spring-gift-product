package gift.dto;

import gift.Product;

public record CreateProductDto(String name, Long price) {

    // validation
    public CreateProductDto {
        if (name == null) throw new IllegalArgumentException("Product name must not be null");
        if (name.isBlank()) throw new IllegalArgumentException("Product name must not be blank");
        if (price == null) throw new IllegalArgumentException("Price is required");
        if (price < 0) throw new IllegalArgumentException("Price must be zero or greater");
    }

    public Product createInstance() {
        return new Product(name, price);
    }
}
