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
     * -> Spring은 @ModelAttribute 바인딩 시, setter를 사용하여 바인딩한다.
     *
     * 그렇다면 왜 Lombok을 쓰지 말라고 하셨을까? 지금 lombok을 사용 안했을 뿐이지, 그냥 평소대로 Getter / Setter 남발하고 있는데
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
