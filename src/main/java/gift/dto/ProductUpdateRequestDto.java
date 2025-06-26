package gift.dto;

public class ProductUpdateRequestDto{
    private String name;
    private Long price;
    private String url;

    public ProductUpdateRequestDto() {
    }

    public ProductUpdateRequestDto(String name, Long price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public Long getPrice() {
        return this.price;
    }

    public String getUrl() {
        return this.url;
    }
}
