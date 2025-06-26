package gift.entity;

import gift.dto.request.CreateProductDto;
import gift.dto.request.UpdateProductDto;

public class Product {

    private final Long id;
    private final String name;
    private final Long price;
    private final String imageUrl;

    public Product(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product copy() {
        return new Product(id, name, price, imageUrl);
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

    // builder
    public static Product build(CreateProductDto dto) {
        return new Product(null, dto.name(), dto.price(), dto.imageUrl());
    }

    public static Product build(UpdateProductDto dto) {
        return new Product(null, dto.name(), dto.price(), dto.imageUrl());
    }
}
