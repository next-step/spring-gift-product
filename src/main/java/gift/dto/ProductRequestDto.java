package gift.dto;

import gift.entity.Product;

public class ProductRequestDto {
    private String name;
    private Long price;
    private String imageUrl;

    public ProductRequestDto(){}

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Long getPrice(){
        return price;
    }
    public void setPrice(Long price){
        this.price = price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(){
        this.imageUrl = imageUrl;
    }

    public Product toEntity(){
        return new Product(null, this.name, this.price, this.imageUrl);
    }
}
