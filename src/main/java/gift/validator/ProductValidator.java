package gift.validator;

import gift.entity.Product;
import java.net.URL;

public class ProductValidator {

    public static void validate(Product product) {
        if (product == null || isBlank(product.getName()) || isBlank(product.getImageUrl())) {
            throw new IllegalArgumentException("상품명, 가격, 이미지 URL은 필수입니다.");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }

        if (!isValidUrl(product.getImageUrl())) {
            throw new IllegalArgumentException("유효한 이미지 URL이 아닙니다.");
        }
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static boolean isValidUrl(String urlStr) {
        try {
            new URL(urlStr).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
