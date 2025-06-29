package gift.dto;

import gift.model.Product;
import java.util.regex.Pattern;

public record ProductRequestDto(String name, int price, String imageUrl) {

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^((http(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$"
    );

    public boolean isValid() {
        return this.name != null && this.price > 0 && isValidUrl(this.imageUrl);
    }

    private boolean isValidUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

    public Product toEntity() {
        return new Product(null, this.name, this.price, this.imageUrl);
    }
}
