package gift.dto;

public class ProductAddRequestDto {
    private String name;
    private Long price;
    private String url;

    public ProductAddRequestDto() {
    }

    public ProductAddRequestDto(String name, Long price, String url) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
