package gift.dto;

import jakarta.validation.constraints.Min;

public class ProductRequestDto {
    private String name;

    @Min(value = 0, message = "가격은 음수일 수 없습니다.")// 유효성 검사 추가
    private int price;
    private String imageUrl;

    public ProductRequestDto() {}

    public ProductRequestDto(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}
