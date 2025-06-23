package gift.dto;

import jakarta.validation.constraints.Min;

public class ProductPatchDto {
    private String name;
    @Min(1)
    private Integer price;
    private String imageUrl;

    public ProductPatchDto() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
