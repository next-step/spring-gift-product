package gift.entity;
import gift.dto.ProductRequestDto;


public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static Product from(ProductRequestDto dto) {
        return new Product(null, dto.getName(), dto.getPrice(), dto.getImageUrl());
    }

    public void setId(Long id) {
        this.id = id;
    }

}
