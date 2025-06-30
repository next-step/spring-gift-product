package gift.dto;

import gift.entity.Product;

public class ResponseDto {

    private Long id;
    private String name;
    private String imageUrl;

    public ResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.imageUrl = product.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
