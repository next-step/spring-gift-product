package gift.dto;

import gift.entity.Item;

public class CreateItemDTO {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public CreateItemDTO() {

    }

    public CreateItemDTO(Item item) {
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
}
