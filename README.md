# spring-gift-product
상품을 추가하고 조회하고 수정하고 삭제 요청할 수 있는 API를 만든다.
## 1. 상품 추가
`POST /api/products`\
name, price, imageURL값을 넣어 상품을 저장한다.

## 2. 상품 조회
`GET /api/products/{id}`\
상품의 id를 입력받아\
상품정보를 받아온다.
## 3. 상품 수정
`PUT /api/products/{id}`\
id를 입력받아\
상품정보를 수정한다.

## 4. 상품 삭제
`DELETE /api/products/{id}`\
id를 입력받아\
해당 상품을 삭제한다.

## 5. 상품 조회를 하였지만 상품이 없을때 적절한 응답 구현
상품이 없을때 404 Not Found로 응답할 수 있게 한다.