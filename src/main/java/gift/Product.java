package gift;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {}

    public Product(Long id, String name, int price, String imageUrl) {
        if(price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, ProductDTO productdto) {
        if(productdto.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.id = id;
        this.name = productdto.getName();
        this.price = productdto.getPrice();
        this.imageUrl = productdto.getImageUrl();
    }

    public void updateProduct(ProductDTO newProductdto) {
        if(newProductdto.getName() != null) {
            this.setName(newProductdto.getName());
        }

        if(newProductdto.getPrice() != 0) {
            this.setPrice(newProductdto.getPrice());
        }

        if(newProductdto.getImageUrl() != null) {
            this.setImageUrl(newProductdto.getImageUrl());
        }
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}