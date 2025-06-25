# spring-gift-product

### 2단계 관리자 화면
---
- [x] 상품 단 건 조회 GET ("/api/products/{productId}")
- 요청 parameter : productId
- 응답 : 해당 id 상품 페이지 (200 OK) </br> "id가 잘못 입력 됨" (404 Not Found)
- [x] 상품 전체 조회 GET ("/api/products")
- 요청 : x
- 응답 : 전체 상품 페이지 (200 OK)
- [x] 상품 추가 POST ("/api/products")
- 요청 : HTML 폼 {productId = 상품id, name = 상품명, price = 가격, imageURL = 상품 이미지URL}
- 응답 : 201 생성됨
- [x] 상품 수정 POST ("/api/products/{productId}")
- 요청 : HTML 폼 {name = 상품명, price = 가격, imageURL = 상품 이미지URL}
- 응답 : 200 OK
- [x] 상품 삭제 ("/api/products/delete")
- 요청 : 상품 id
- 응답 : 200 OK </br> "id가 잘못 입력 됨" (404 Not Found)
