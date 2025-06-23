package gift.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void 상품_가격이_음수이면_예외가_발생한다() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.of("example", -1, "url"));
        assertEquals("가격은 음수일 수 없습니다.", exception.getMessage());
    }

    @Test
    void 상품_가격이_0일_때_생성된다() {
        assertDoesNotThrow(() -> Product.of("사과", 100, "image.png"));
    }

}
