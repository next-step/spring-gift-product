package gift.dto;

import jakarta.validation.constraints.NotNull;

public class ProductRequestDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    private String imageUrl;
    public Long getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
