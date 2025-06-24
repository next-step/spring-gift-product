package gift;

public record Product(Long id, String name, Integer price, String imageUrl) {

    public String showProductInfo() {
        return "상품명: %s, 가격: %d".formatted(name, price);
    }
}