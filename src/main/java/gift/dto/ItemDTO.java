package gift.dto;

import gift.entity.Item;
import jakarta.validation.constraints.Min;

public class ItemDTO {
    private Long id;
    private String name;
    @Min(0)
    private Integer price;
    private String imageUrl;

    public ItemDTO() {
    }


    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.imageUrl = item.getImageUrl();

    }

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


    /***
     * 왜 setter를 설정해놓으니까, HTML 폼에 저장이 될까???
     *
     */
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
