package gift;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Product {

    private Long id;

    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    @Positive(message = "가격은 0보다 커야 합니다.")
    private int price;

    @NotBlank(message = "이미지 URL을 입력해주세요.")
    private String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product() {

    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
