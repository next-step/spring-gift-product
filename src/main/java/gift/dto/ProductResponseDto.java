package gift.dto;

import gift.entity.Product;

public class ProductResponseDto{
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public ProductResponseDto(Product product){
            this.id = product.getId();
            this.name = product.getName();
            this.price = product.getPrice();
            this.imageUrl = product.getImageUrl();
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Integer getPrice(){
        return this.price;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
}