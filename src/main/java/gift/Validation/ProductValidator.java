package gift.Validation;

import gift.Model.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductValidator {

    public static Map<String, String> validate(Product product) {
        Map<String, String> errors = new HashMap<>();

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            errors.put("name", "상품 이름이 비어있습니다.");
        }

        if (product.getPrice() < 0) {
            errors.put("price", "상품 가격은 0 이상이어야 합니다.");
        }

        if (product.getImgURL() == null || product.getImgURL().trim().isEmpty()) {
            errors.put("imgURL", "이미지 URL이 비어 있습니다.");
        }

        return errors;
    }
}
