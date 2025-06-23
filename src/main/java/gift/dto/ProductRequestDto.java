package gift.dto;

public class ProductRequestDto {
    Long id;
    String name;
    int price;
    String imageUrl;

    public ProductRequestDto(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
