# spring-gift-product

## 상품 관리 - 1단계 과제: 상품 API
- HashMap<>을 이용해 임시 데이터베이스를 구현했습니다. key로는 Product.id를 사용합니다.
- 사용자가 상품의 id를 숙지하고 있다고 가정하고 구현했습니다. 생성/수정 요청 시에 사용자가 상품 id를 전달해야 합니다.


0. 예외처리
- 상품 id나 요청 객체가 null인 경우 NOT_EXSISTS_PRODUCT(InvalidProductException)
- 생성/수정 요청 시 요청 객체에 null인 필드값이 있는 경우 INVALID_PRODUCT_REQUEST(InvalidProductException)
- 생성/수정 요청 시 상품 가격이 음수인 경우 INVALID_PRODUCT_PRICE(InvalidProductException)
- 전달받은 상품 id에 해당하는 객체가 데이터베이스에 없는 경우 NOT_EXSISTS_PRODUCT(InvalidProductException)
- 이외 다른 예외는 INTERNAL_SERVER_ERROR

1. 상품 생성
- POST: /api/products
- request: {상품 id, 상품 이름, 상품 가격, 상품 이미지 url}
- response: 201(성공), {}

2. 단일 상품 조회
- GET: /api/products/{productId}
- request: {}
- response: 200(성공), {상품 이름, 상품 가격, 상품 이미지 url}

3. 상품 목록 조회
- GET: /api/products/all
- request: {}
- response: 200(성공), {{상품 이름1, 상품 가격1, 상품 이미지 url1}, {상품 이름2, 상품 가격2, 상품 이미지 url2}...}

4. 상품 정보 수정
- PATCH: /api/products/{productId}
- request: {상품 id, 상품 이름, 상품 가격, 상품 이미지 url}
- response: 204(성공), {}

5. 상품 삭제
- DELETE: /api/products/{productId}
- request: {}
- response: 204(성공), {}