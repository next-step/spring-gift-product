package gift.dto;

public class ProductRequestDto {
    private final String name;
    private final int price;
    private final String imgUrl;

    public ProductRequestDto(String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
