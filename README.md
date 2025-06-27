# spring-gift-product


#### 관리자 화면 구현

| 구현 기능            |
|------------------|
| 물품 등록            |
| 물품 수정            |
| 같은 열에 존재하는 물품 삭제 |
| 선택한 물품들 모두 삭제    |

#### H2 DB 적용 및 Repository 구조 리팩토링

- **Feat: H2 DB 설정 및 초기 데이터 스크립트 적용**
    - H2 in-memory DB 도입 및 application.properties 설정
    - `schema.sql`, `data.sql`을 통해 product 테이블 구조와 초기 데이터 삽입
    - `spring.sql.init.mode=always` 등 SQL 스크립트 자동 실행 설정

- **Refactor: 임시 Map 저장소를 H2 DB 연동 방식으로 리팩토링**
    - 기존의 `Map<Long, Product>` 형태의 임시 저장소 제거
    - `JdbcTemplate` 기반의 실제 DB 연동 Repository 구현
    - DB 기반 저장/조회/수정/삭제 기능으로 대체

- **Refactor: Repository의 DTO 반환 책임을 제거하여 SRP 준수**
    - Repository에서 `ProductResponseDto` 반환 제거
    - DB에서 도메인 객체(`Product`)만 반환하도록 수정
    - DTO 변환은 Service 계층에서 처리하도록 역할 분리

#### API 명세서

- 상품 API

| URL | Method | 기능 | 설명                   |
|--------------|--------|-----------|----------------------|
| /api/products | POST   |  물품 등록 | 새 물품을 등록한다.          |
| /api/products/{id} | GET    | 물품 단건 조회 | id 값으로 물품 하나를 조회한다.  |
| /api/products | GET    |  물품 목록 조회 | 모든 물품을 조회한다. |
| /api/products/{id} | PUT    | 물품 수정 | id 값에 매핑된 물품 하나를 수정한다. |
| /api/products/{id} | DELETE | 물품 삭제 | id 값에 매핑된 물품 하나를 삭제한다. |


#### 커밋 컨벤션


| type | meaning            |
| ----- |--------------------|
| feat | 기능 추가              |
| fix | 버그 수정              |
| docs | 문서 변경 (주석, README) |
| style | format 변경          |
| refactor | 리팩토링               |
| test | 테스트 코드 추가/수정       |
| chore | 유지보수 작업            |