# spring-gift-product

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