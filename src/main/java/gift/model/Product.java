package gift.model;

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateFrom(Product updatedProduct) {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("업데이트할 상품 정보가 null입니다");
        }

        if (updatedProduct.getName() != null) {
            this.name = updatedProduct.getName();
        }

        if (updatedProduct.getPrice() >= 0) {
            this.price = updatedProduct.getPrice();
        }

        if (updatedProduct.getImageUrl() != null) {
            this.imageUrl = updatedProduct.getImageUrl();
        }
    }
}
