package gift.product.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

  @Test
  void of_정상_생성() {
    Product product = Product.of("applePie", 3000, "sweet honey applePie", "http://aaa.com/apple.png");

    assertNull(product.id());
    assertEquals("applePie", product.name());
    assertEquals(3000, product.price());
    assertEquals("sweet honey applePie", product.description());
    assertEquals("http://aaa.com/apple.png", product.imageUrl());
  }

  @Test
  void withId_정상_생성() {
    Product product = Product.withId(1L, "applePie", 3000, "sweet honey applePie", "http://aaa.com/apple.png");

    assertEquals(1L, product.id());
    assertEquals("applePie", product.name());
    assertEquals(3000, product.price());
    assertEquals("sweet honey applePie", product.description());
    assertEquals("http://aaa.com/apple.png", product.imageUrl());
  }

  @Test
  void name_null이면_예외() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        Product.of(null, 1000, "설명", "http://img.com")
    );
    assertEquals("상품명은 null일 수 없습니다.", e.getMessage());
  }

  @Test
  void name_빈값이면_예외() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        Product.of("", 1000, "설명", "http://img.com")
    );
    assertEquals("상품명은 빈 값일 수 없습니다", e.getMessage());
  }

  @Test
  void price_음수면_예외() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        Product.of("초코파이", -1, "설명", "http://img.com")
    );
    assertEquals("상품 가격은 음수일 수 없습니다", e.getMessage());
  }

  @Test
  void imageUrl_빈값이면_예외() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        Product.of("초코파이", 1000, "설명", "")
    );
    assertEquals("이미지 URL은 빈 값일 수 없습니다", e.getMessage());
  }

  @Test
  void withId_id가_null이면_예외() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        Product.withId(null, "초코파이", 1000, "설명", "http://img.com")
    );
    assertEquals("Id는 null일 수 없습니다.", e.getMessage());
  }

  @Test
  void withId_id가_음수면_예외() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        Product.withId(-10L, "초코파이", 1000, "설명", "http://img.com")
    );
    assertEquals("Id는 음수일 수 없습니다", e.getMessage());
  }
}
