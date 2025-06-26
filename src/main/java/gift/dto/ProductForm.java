package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductForm {

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    @NotNull(message = "가격은 필수입니다.")
    private Integer price;

    @NotBlank(message = "이미지 URL은 필수입니다.")
    private String imageUrl;

    public ProductForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductRequest toRequest() {
        return new ProductRequest(name, price, imageUrl);
    }

    public static ProductForm from(ProductResponse response) {
        ProductForm form = new ProductForm();
        form.setName(response.name());
        form.setPrice(response.price());
        form.setImageUrl(response.imageUrl());
        return form;
    }
}