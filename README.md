# spring-gift-product

## step1 - 상품 API

### 📌 목표

- 상품(Product)을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- JSON 포맷으로 HTTP 요청 및 응답을 처리한다.
- 현재는 DB를 사용하지 않으며, 자바 컬렉션을 활용한 메모리 기반 저장소를 사용한다.
- 그러나 향후 DB 교체를 고려하여 설계를 유연하게 구성해야 한다.

### 🔨 기능 목록

1. Product 도메인 (Value Object)
    - 해당 객체는 불변(Immutable) 객체로 구현한다.
    - price < 0인 경우, IllegalArgumentException을 발생시킨다.

   | 필드명      | 타입        | 제약 조건            |
      |----------|-----------|------------------|
   | name     | `String`  | null 또는 빈 문자열 불가 |
   | price    | `Integer` | 0 이상             |
   | imageUrl | `String`  | null 또는 빈 문자열 불가 |
