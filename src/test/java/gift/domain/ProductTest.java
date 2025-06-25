package gift.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @Test
    void 모든_필드가_유효할_때_상품_생성_성공_및_ID는_null_확인() {
        Product product = Product.of("사과", 1000, "apple.jpg");

        assertNotNull(product);
        assertEquals("사과", product.name());
        assertEquals(1000, product.price());
        assertEquals("apple.jpg", product.imageUrl());
        assertEquals(null, product.id());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void 제품명_또는_이미지_URL이_null_비어있거나_공백일_때_IllegalArgumentException_발생(String invalidInput) {
        IllegalArgumentException nameException = assertThrows(IllegalArgumentException.class,
                () -> Product.of(invalidInput, 100, "valid_url.png"));
        assertEquals("name은 비어있거나 null일 수 없습니다.", nameException.getMessage());

        IllegalArgumentException imageUrlException = assertThrows(IllegalArgumentException.class,
                () -> Product.of("Valid Name", 100, invalidInput));
        assertEquals("imageUrl은 비어있거나 null일 수 없습니다.", imageUrlException.getMessage());
    }

    @Test
    void 가격이_음수일_때_IllegalArgumentException_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.of("test_product", -1, "test_url.png"));
        assertEquals("가격은 null이거나 음수일 수 없습니다.", exception.getMessage());
    }

    @Test
    void 가격이_0일_때_상품_생성_성공() {
        assertDoesNotThrow(() -> Product.of("무료 상품", 0, "free.gif"));
        Product product = Product.of("제로 음료", 0, "zero.png");
        assertEquals(0, product.price());
    }

    @Test
    void 유효한_ID_포함_시_상품_생성_성공() {
        Product product = Product.withId(1L, "테스트 상품", 5000, "test_item.jpg");

        assertNotNull(product);
        assertEquals(1L, product.id());
        assertEquals("테스트 상품", product.name());
        assertEquals(5000, product.price());
        assertEquals("test_item.jpg", product.imageUrl());
    }

    @Test
    void ID가_null일_때_IllegalArgumentException_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.withId(null, "some_name", 100, "some_url.png"));
        assertEquals("ID는 null일 수 없습니다.", exception.getMessage());
    }

    @Test
    void 상품_정보_업데이트_시_기존_객체는_불변하고_새로운_Product_객체_반환() {
        Product originalProduct = Product.withId(1L, "Original", 100, "original.png");
        Product updatedProduct = originalProduct.update("Updated", 200, "updated.png");

        assertEquals(1L, originalProduct.id());
        assertEquals("Original", originalProduct.name());
        assertEquals(100, originalProduct.price());
        assertEquals("original.png", originalProduct.imageUrl());

        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.id());
        assertEquals("Updated", updatedProduct.name());
        assertEquals(200, updatedProduct.price());
        assertEquals("updated.png", updatedProduct.imageUrl());
    }
}