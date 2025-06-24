package gift.dto;

public record UpdateProductDto(String name, Long price, String imageUrl) {

    // validation
    public UpdateProductDto {
        if (name != null && name.isBlank()) throw new IllegalArgumentException("Product name must not be blank");
        if (price != null && price < 0) throw new IllegalArgumentException("Price must be zero or greater");
    }
}
