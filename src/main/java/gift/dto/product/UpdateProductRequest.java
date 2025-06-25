package gift.dto.product;

public record UpdateProductRequest(String name, Integer price, Integer quantity) {
    public static UpdateProductRequest from(ProductManageResponse response) {
        return new UpdateProductRequest(response.name(), response.price(), response.quantity());
    }
}
