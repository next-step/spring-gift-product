package gift.validator;

import gift.dto.ProductUpdateRequestDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.net.URL;
import java.util.NoSuchElementException;

public class ProductValidator {

    public static void validate(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("상품 정보가 비어있습니다.");
        }

        validateField(product.getName(), product.getPrice(), product.getImageUrl());
    }

    public static void validateUpdate(ProductUpdateRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("상품 정보가 비어있습니다.");
        }

        validateField(dto.getName(), dto.getPrice(), dto.getImageUrl());
    }

    public static void validateField(String name, Integer price, String imageUrl) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (price == null) {
            throw new IllegalArgumentException("가격은 필수입니다.");
        }

        if (isBlank(imageUrl)) {
            throw new IllegalArgumentException("이미지 URL은 필수입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }

        if (!isValidUrl(imageUrl)) {
            throw new IllegalArgumentException("유효한 이미지 URL이 아닙니다.");
        }
    }

    public static Product validateExists(Long id, ProductRepository repository) {
        return repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));
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
