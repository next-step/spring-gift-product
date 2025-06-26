# spring-gift-product

## 1단계
### PRODUCT API 명세서

`상품 등록`
- /api/products
- POST
- 새 상품을 등록한다.

`상품 단건 조회`
- /api/products/{id}
- GET
- id 값으로 상품 단건을 조회한다.

`상품 전체 조회`
- /api/products
- GET
- 상품 전체를 조회한다.

`상품 수정`
- /api/products/{id}
- PUT
- id 값과 매핑된 상품 하나의 정보를 수정한다.

`상품 삭제`
- /api/products/{id}
- DELETE
- id 값과 매핑된 상품 하나를 삭제한다.


## 2단계
### 구현 기능 목록
- 1단계 리뷰 요청 반영
- 상품 조회 템플릿 구현
- 상품 생성, 수정 템플릿 구현
- 상품 삭제 기능 구현
- View Controller 구현
