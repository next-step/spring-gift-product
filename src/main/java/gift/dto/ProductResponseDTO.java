package gift.dto;

public class ProductResponseDTO {
    private final Integer id;
    private final String name;
    private final Integer price;
    private final String imageUrl;

    public ProductResponseDTO(Integer id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
