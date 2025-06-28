package gift.dto;

import java.math.BigInteger;

public class ProductResponseDTO {
    private final Integer id;
    private final String name;
    private final BigInteger price;
    private final String imageUrl;

    public ProductResponseDTO(Integer id, String name, BigInteger price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigInteger getPrice() {
        return price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
