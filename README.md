# spring-gift-product

## step1 - 상품 API

### 📌 목표

- 상품(Product)을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- JSON 포맷으로 HTTP 요청 및 응답을 처리한다.
- 현재는 DB를 사용하지 않으며, 자바 컬렉션을 활용한 메모리 기반 저장소를 사용한다.
- 그러나 향후 DB 교체를 고려하여 설계를 유연하게 구성해야 한다.

### 🔨 기능 목록

1. **Product 도메인 (Immutable 객체)**

    * `Product` 객체는 자바 `record`를 활용해 불변(Immutable)으로 구현한다.
    * 객체 생성 시 다음 조건을 엄격하게 검증하며, 조건을 위반하면 `IllegalArgumentException`을 발생시킨다.

   | 필드명      | 타입        | 제약 조건                                 |
      | -------- | --------- | ------------------------------------- |
   | id       | `Long`    | null일 수 있으나, 명시적 생성 시에는 null 불가 (필수값) |
   | name     | `String`  | null 또는 빈 문자열, 공백 문자만 포함할 수 없음        |
   | price    | `Integer` | null 불가, 0 이상인 정수만 가능                 |
   | imageUrl | `String`  | null 또는 빈 문자열, 공백 문자만 포함할 수 없음        |

    * **객체 생성 메서드**

        * `of(String name, Integer price, String imageUrl)` : ID 없이 생성 (ID는 null)
        * `withId(Long id, String name, Integer price, String imageUrl)` : ID 포함 생성, `id`가 null이면 예외
          발생

    * **업데이트 메서드**

        * `update(String name, Integer price, String imageUrl)` : 불변 객체 특성에 따라 기존 `id`를 유지하며, 새
          필드값으로 새 `Product` 객체를 반환
