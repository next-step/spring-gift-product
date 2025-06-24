package gift.product.entity;

public class Product {

    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;
    
    //자동으로 생성되지만 가독성을 위해 생성
    public Product() {}

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
