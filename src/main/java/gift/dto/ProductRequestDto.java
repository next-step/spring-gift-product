package gift.dto;

public class ProductRequestDto {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}