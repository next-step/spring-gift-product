package gift.dto;

public class ProductRequestDto {

    private String name;
    private Long price;
    private String imageUrl;

    public ProductRequestDto(Long price, String name, String imageUrl) {
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
