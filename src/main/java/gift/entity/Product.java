package gift.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "price", "imageUrl"})
public class Product {

    // DB에서 자동 생성되는 ID는 등록 시점에는 null일 수 있으므로 Long 사용
    // 또한 Product는 불변 객체이므로 생성자에서만 값을 설정할 수 있도록 설계
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {}

    /**
     * DB에서 자동 생성되는 ID를 포함한 전체 필드 생성자
     * 등록 시에는 id가 null 이므로 Long 으로 처리
     * Product는 불변 객체이기 때문에 생성자에서 모든 필드를 설정함
     */
    public Product(Long id, String name, int price, String imageUrl) {
        validate(name, price, imageUrl);

        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // 등록 시 사용하는 생성자
    public Product(String name, int price, String imageUrl) {
        this(null, name, price, imageUrl);  // 위 생성자 재사용
    }

    private void validate(String name, int price, String imageUrl) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }

        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("이미지 URL은 필수입니다.");
        }

        try {
            new java.net.URL(imageUrl).toURI();
        } catch (Exception e) {
            throw new IllegalArgumentException("유효한 이미지 URL이 아닙니다.");
        }
    }

    public long getId() {
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

}
