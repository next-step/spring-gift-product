package gift.dto;

import gift.entity.Item;

public class CreateItemDto {

    private Long id;
    private String name;
    private Integer price;
    private String ImageUrl;

    //생성자
    protected CreateItemDto() {}

    public CreateItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.ImageUrl = item.getImageUrl();
    }

    //getter
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
        return ImageUrl;
    }
}
