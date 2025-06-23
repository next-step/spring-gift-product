package gift.domain;

import gift.dto.product.CreateProductRequest;

public class Product {

    private static Long count = 1L;

    private Long id;

    private String name;
    private Integer price;
    private Integer quantity;

    private Product(String name, Integer price, Integer quantity) {
        this.id = count;
        count++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }


    public static Product of(CreateProductRequest request) {
        return new Product(request.name(), request.price(), request.quantity());
    }
}
