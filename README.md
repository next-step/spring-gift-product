# spring-gift-product

## STEP3 상품 DB 연동

상품 데이터를 메모리가 아닌 H2 데이터베이스에 영구 저장하도록 기능을 전환합니다.  
기존의 Map 기반 저장소는 모두 제거되며, `JdbcTemplate` 기반의 구현체를 통해 CRUD 기능을 제공합니다.  
테이블 생성은 `schema.sql`을 통해 자동으로 초기화됩니다.

### 구현할 기능 목록

1. **H2 데이터베이스 연결 설정**
   - `application.yml`을 통해 H2 데이터베이스 구성
   - JDBC URL, 드라이버, H2 콘솔 설정 포함

2. **DB 초기화 SQL 스크립트 작성**
   - `schema.sql`을 이용하여 `products` 테이블 자동 생성
   - 필요 시 `data.sql`로 초기 상품 데이터 삽입

3. **JdbcTemplate 기반 Repository 구현**
   - 기존 Map 컬렉션 기반 Repository 제거
   - `JdbcProductRepository`를 새로 작성
   - `JdbcTemplate` 및 `SimpleJdbcInsert`를 활용

4. **예외 처리 개선**
   - 존재하지 않는 상품 ID 접근 시 `ProductNotFoundException` 발생
   - 글로벌 예외 핸들러에서 JSON으로 메시지 반환

---

> 💡 이번 단계의 핵심은 **영속성 적용**과 **JdbcTemplate 사용법 숙지**입니다.