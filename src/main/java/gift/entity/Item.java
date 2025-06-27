package gift.entity;

public class Item {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Item(Long id, String name, int price, String imageUrl) {
        if (id == null) {
            throw new IllegalArgumentException("상품 ID는 비어있을 수 없습니다.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public void updateItemInfo(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
