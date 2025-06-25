package gift.domain;

public record Product(Long id, String name, Integer price, String imageUrl) {

    public Product {
        validateName(name);
        validateImageUrl(imageUrl);
        validatePrice(price);
    }

    public static Product of(String name, Integer price, String imageUrl) {
        return new Product(null, name, price, imageUrl);
    }

    public static Product withId(Long id, String name, Integer price, String imageUrl) {
        validateId(id);
        return new Product(id, name, price, imageUrl);
    }

    public Product update(String name, Integer price, String imageUrl) {
        requireAtLeastOneFieldChanged(name, price, imageUrl);

        return new Product(
                this.id,
                isNotBlank(name) ? name : this.name,
                isValidPrice(price) ? price : this.price,
                isNotBlank(imageUrl) ? imageUrl : this.imageUrl
        );
    }

    private static boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    private static boolean isValidPrice(Integer price) {
        return price != null && price >= 0;
    }

    private static boolean isAllFieldsBlankOrInvalid(String name, Integer price, String imageUrl) {
        return !isNotBlank(name) && !isValidPrice(price) && !isNotBlank(imageUrl);
    }

    private static void requireAtLeastOneFieldChanged(String name, Integer price, String imageUrl) {
        if (isAllFieldsBlankOrInvalid(name, price, imageUrl)) {
            throw new IllegalArgumentException("최소 하나 이상의 필드를 변경해야 합니다.");
        }
    }

    private static void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID는 null일 수 없습니다.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("ID는 음수일 수 없습니다.");
        }
    }

    private static void validateName(String name) {
        if (!isNotBlank(name)) {
            throw new IllegalArgumentException("name은 비어있거나 null일 수 없습니다.");
        }
    }

    private static void validateImageUrl(String imageUrl) {
        if (!isNotBlank(imageUrl)) {
            throw new IllegalArgumentException("imageUrl은 비어있거나 null일 수 없습니다.");
        }
    }

    private static void validatePrice(Integer price) {
        if (!isValidPrice(price)) {
            throw new IllegalArgumentException("가격은 null이거나 음수일 수 없습니다.");
        }
    }
}

