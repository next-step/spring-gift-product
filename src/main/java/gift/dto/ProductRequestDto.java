package gift.dto;

import java.math.BigDecimal;

public class ProductRequestDto {
    private final String name;
    private final BigDecimal price;
    private final String imgUrl;

    public ProductRequestDto(String name, BigDecimal price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
