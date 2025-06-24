package gift.dto;

import gift.entity.Product;

public class FindProductResponseDto {
    
    private final Long id; //상품의 id
    private final String name; //상품의 이름
    private final Long price; //상품의 가격
    private final String imageUrl; //상품의 이미지 URL
    private final Long dbId;
    
    public FindProductResponseDto(Long id, String name, Long price, String imageUrl, Long dbId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.dbId = dbId;
    }
    
    public FindProductResponseDto(Long dbId, Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.dbId = dbId;
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
    
    public Long getDbId() {
        return dbId;
    }
}
