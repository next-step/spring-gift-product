package gift.dto;

import gift.entity.Product;

public class FindProductResponseDto {
    
    private final Long id; //상품의 id
    private final String name; //상품의 이름
    private final Long price; //상품의 가격
    private final String imageUrl; //상품의 이미지 URL
    
    public FindProductResponseDto(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    
    public FindProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
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
