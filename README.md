# spring-gift-product
# 3단계
jdbcClient를 사용하여 상품정보를 저장할 수 있는 데이터베이스를 만들고 sql문을 작성하여 관리할 수 있게 한다.
## 1. 2단계 피드백 반영
- RequestMapping에서 api 빼기
- service, repository 를 interface 타입으로 주입해보기
- toProductResponseDto 생성자 수정
- record 클래스를 뷰에 전달해보기
## 2. h2데이터베이스 설정
- h2데이터베이스를 사용하기 위한 설정하기
## 3. schema.sql, data.sql 만들기
- schema.sql파일에 상품 데이터베이스 product 테이블 정의하기
- data.sql에 테스트 데이터 추가하는 코드 추가하기
## 4. 데이터베이스 내 상품 목록 조회 기능 만들기
- `select id, name, price, url from product`
## 5. 데이터베이스 내 상품 추가 기능 만들기
- `insert into product (id, name, price, url) values (:id, :name, :price, :url)`
## 6. 데이터베이스 내 상품 업데이트 기능 만들기
- `update product set name=:name, price=:price, url=:url where id = :id`
## 7. 데이터베이스 내 상품 삭제 기능 만들기
- `delete from product where id = :id`
## 8. 추가작업
- addProduct와 deleteProductById가 같은 dto를 반환하는데, 코드 구조가 달라  통일 성을 해친다.
- -> addProduct와 deleteProductById가 dto를 반환할 필요가 없다고 판단했다.
- -> dto를 반환하지 않는 것으로 통일하기
- 예외 발생 조건에 Optional 적용해보기
## 9. 피드백 반영
- 생성자도 인터페이스에 의존하게 하기 (실수로 안바꿨네요 ㅠㅠ)
- 상품 수정 결과를 알 수 잇게 int값 반환
- 레포지터리의 addProduct가 DTO를 받지 않고 Product를 받도록 하기 
- (상품을 생성하기 전에는 id값이 없어서 Product 엔티티가 아닌 DTO를 넘겨주었었는데 생성자에 null값을 넣어서 구현해보겠습니다.)

---
# 2단계
상품을 추가, 조회, 수정, 삭제 할 수 있는 관리자 화면을 만든다.
## 1. 1단계 피드백 반영
- 파라미터에 DTO자체를 전달
- entity를 service계층에서 만들어서 repository에 전달
- 메소드이름 더 디테일하게 수정
- 더 구체적인 응답을 주는 ResponseEntity 만들기
- IDE의 reformat 사용해보기
- url path 수정
## 2. 상품리스트 받아오기
- 상품 전체조회 기능 만들기
- 상품정보를 받아와서 한줄씩 나열하기
- api/admin/products에 GET요청
## 3. 상품추가 폼 및 버튼 만들기
- 상품정보를 폼으로 입력받아 버튼을 누를 시
- api/admin/products/add에 POST요청
## 4. 상품수정 폼 및 버튼 만들기
- 수정 버튼은 상품정보마다 하나씩
- 상품정보를 폼에서 수정하여 버튼을 누를 시
- api/admin/products/edit/{id}에 PUT요청
## 5. 상품삭제 버튼 만들기
- 삭제 버튼은 상품정보마다 하나씩
- admin/api/products/delete/{id}에 DELETE요청
## 6. 추가적인 작업
- 추가, 수정 폼의 요소들을 정렬해서 깔끔하게 만들어보기
- 이미지가 url이 아닌 사진으로 보이게 구현해보기
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