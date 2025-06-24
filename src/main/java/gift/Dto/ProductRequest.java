package gift.Dto;

import gift.Model.*;

public class ProductRequest {
    private final String name;
    private final int price;
    private final String imgUrl;

    public ProductRequest(String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ProductName toName() {
        return new ProductName(name);
    }

    public Price toPrice() {
        return new Price(price);
    }

    public ImgUrl toImgUrl() {
        return new ImgUrl(imgUrl);
    }

    public boolean isValid() {
        try {
            toName();
            toPrice();
            toImgUrl();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
