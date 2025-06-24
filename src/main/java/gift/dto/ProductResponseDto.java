package gift.dto;

public class ProductResponseDto {

    private Long id;
    private String name;
    private Double price;
    private String imageUrl;

    public ProductResponseDto(Long id, String name, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
