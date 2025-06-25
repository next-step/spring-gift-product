package gift.dto.response;

public record ProductResponse(Long id, String name, int price, String imageUrl) {
    public ProductResponse(gift.domain.Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}