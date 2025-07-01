package gift.entity;

public record Product(
        Long id,
        String name,
        int price,
        String imageUrl
) {

    // Factory method
    public static Product createProduct(Long id, String name, int price, String imageUrl) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        return new Product(id, name, price, imageUrl);
    }

    public Product withId(Long id) {
        return new Product(id, this.name, this.price, this.imageUrl);
    }

    public Product withName(String name) {
        return new Product(this.id, name, this.price, this.imageUrl);
    }

    public Product withPrice(int price) {
        return new Product(this.id, this.name, price, this.imageUrl);
    }

    public Product withImageUrl(String imageUrl) {
        return new Product(this.id, this.name, this.price, imageUrl);
    }
}
