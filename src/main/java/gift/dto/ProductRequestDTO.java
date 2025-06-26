package gift.dto;

import java.math.BigInteger;

public class ProductRequestDTO {
    private final String name;
    private final BigInteger price;
    private final String imageUrl;

    public ProductRequestDTO(String name, BigInteger price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }
    public BigInteger getPrice() { return price; }
    public String getImageUrl() {
        return imageUrl;
    }
}
