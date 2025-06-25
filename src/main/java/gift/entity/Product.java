package gift.entity;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public ProductResponseDto toDto() {
            return new ProductResponseDto(id, name, price, imageUrl);
    }

    public void updatePrice(int price) {
        if(price < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price will be over 0");
        this.price = price;
    }
    public void updateProductInfo(String name, int price, String imageUrl) {
        if(name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }
        if(price < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price will be over 0");
        }
        if(imageUrl == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image url is required");
        }
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Long getId() {
        return id;
    }


}
