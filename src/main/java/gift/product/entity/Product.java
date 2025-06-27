package gift.product.entity;

import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductUpdateRequestDto;

public class Product {

    private final Long id;
    private String name;
    private Long price;
    private String imageUrl;

    public Product(Long id, ProductCreateRequestDto dto) {
        this.id = id;
        this.name = dto.name();
        this.price = dto.price();
        this.imageUrl = dto.imageUrl();
    }

    public void update(ProductUpdateRequestDto dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (price != null) {
            this.price = dto.price();
        }
        if (imageUrl != null) {
            this.imageUrl = dto.imageUrl();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
