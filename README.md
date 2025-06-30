# spring-gift-product

1. 상품 조회, 추가, 수정, 삭제 HTTP API 구현

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

### 상품 API

|URL|메서드|기능|설명|성공 시 상태코드|
|--|--|--|--|--|
|/api/products|POST|상품 생성|새 상품 등록|201 Created|
|/api/products/{productId}|GET|상품 단건 조회|특정 상품의 정보 조회|200 OK|
|/api/products|GET|상품 전체 조회|모든 상품의 정보 조회|200 OK|
|/api/products/{productId}|PUT|상품 수정|기존 상품의 정보 수정|204 No Content|
|/api/products/{productId}|DELETE|상품 단건 삭제|특정 상품 삭제|204 No Content|
|/api/products|DELETE|상품 전체 삭제|모든 상품 삭제|204 No Content|


2. 관리자 화면 구현

- 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

### 관리자 화면 URL 정의

|URL|메서드|설명|
|--|--|--|
|/management/products|GET|상품 목록 페이지 (목록 조회)|
|/management/products/new|GET|상품 등록 페이지|
|/management/products/{id}|GET|상품 수정 페이지|

3. 데이터베이스 적용

### 데이터베이스 연동: H2 & Spring JDBC

- 상품 정보 관리를 위해 H2 데이터베이스를 사용한다.
- `SimpleJdbcInsert` 및 `JdbcClient`를 활용하여 구현한다.