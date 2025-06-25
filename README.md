# Gift-product
## 상품 관리 API 설계

- 상품 등록
  - 상품을 등록
  - /products
  - POST

- 상품 조회
  - 전체 상품의 목록을 조회
  - /products
  - GET

- 상품 단건 조회
  - id 값으로 단일 상품을 조회
  - /products/{id}
  - GET

- 상품 수정
  - id 값으로 단일 상품 수정
  - /products/{id}
  - PATCH

- 상품 삭제
  - id 값으로 해당 상품 삭제
  - /products/{id}
  - DELETE