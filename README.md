# spring-gift-product
# 2단계
상품을 추가, 조회, 수정, 삭제 할 수 있는 관리자 화면을 만든다.
## 1. 1단계 피드백 반영
- 파라미터에 DTO자체를 전달
- entity를 service계층에서 만들어서 repository에 전달
- 메소드이름 더 디테일하게 수정
- 더 구체적인 응답을 주는 ResponseEntity 만들기
- IDE의 reformat 사용해보기
- url path 수정
## 2. 상품추가 폼 및 버튼 만들기
- 상품정보를 폼으로 입력받아 버튼을 누를 시
- api/products에 POST요청
## 3. 상품리스트 받아오기
- 상품정보를 받아와서 한줄씩 나열하기
## 4. 상품수정 폼 및 버튼 만들기
- 수정 버튼은 상품정보마다 하나씩
- 상품정보를 폼에서 수정하여 버튼을 누를 시
- api/products/{id}에 PUT요청
## 5. 상품삭제 버튼 만들기
- 삭제 버튼은 상품정보마다 하나씩
- api/products/{id}에 DELETE요청
---
# 1단계 
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