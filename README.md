# spring-gift-product
---

### 1단계 상품 API

- [x] 상품 단 건 조회 GET ("/api/products/{productId}")
- 요청 parameter : productId
- 응답 : 200 OK<pre>{
  "productId": 상품id,</br>
  "name": "상품명",</br>
  "price": "가격",</br>
  "imageURL": "이미지URL"</br>
  }</pre></br>

- [x] 상품 추가 POST ("/api/products")
- 요청 : 201 CREATED<pre>{
  "productId": 상품id,</br>
  "name": "상품명",</br>
  "price": "가격",</br>
  "imageURL": "이미지URL"</br>
  }</pre>
- 응답 : <pre>{
  "productId": 상품id,</br>
  "name": "상품명",</br>
  "price": "가격",</br>
  "imageURL": "이미지URL"</br>
  }</pre></br>
- [x] 상품 수정 POST ("/api/products/{productId}")
- 요청 : <pre>{
  "productId": 상품id,</br>
  "name": "상품명",</br>
  "price": "가격",</br>
  "imageURL": "이미지URL"</br>
  }</pre>
- 응답 : 200 OK <pre>{
  "productId": 상품id,</br>
  "name": "상품명",</br>
  "price": "가격",</br>
  "imageURL": "이미지URL"</br>
  }</pre></br>
- [x] 상품 삭제 ("/api/products/{productId}")
- 요청 parameter : productId
- 응답 : 200 OK<pre>{
  "productId": 상품id,</br>
  "name": "상품명",</br>
  "price": "가격",</br>
  "imageURL": "이미지URL"</br>
  }</pre></br>

---

### 2단계 관리자 화면

---

- [x] 상품 단 건 조회 GET ("/home/{productId}")
- 요청 parameter : productId
- 응답 : 해당 id 상품 페이지 (200 OK) </br> "id가 잘못 입력 됨" (404 Not Found)
- [x] 상품 전체 조회 GET ("/home")
- 요청 : x
- 응답 : 전체 상품 페이지 (200 OK)
- [x] 상품 추가 POST ("/home")
- 요청 : HTML 폼 {productId = 상품id, name = 상품명, price = 가격, imageURL = 상품 이미지URL}
- 응답 : 201 생성됨
- [x] 상품 수정 POST ("/home/{productId}")
- 요청 : HTML 폼 {name = 상품명, price = 가격, imageURL = 상품 이미지URL}
- 응답 : 200 OK
- [x] 상품 삭제 ("/home/delete")
- 요청 : 상품 id
- 응답 : 200 OK </br> "id가 잘못 입력 됨" (404 Not Found)

---

### 3단계 데이터베이스 적용

- 데이터베이스 연동
- [x] 저장공간을 hashMap -> h2로 바꾸기
- [x] productRepository가 JDBC를 활용하여 h2에 접근하도록 변경
- [x] dto를 사용해 ProductController, ProductManagerViewController에서 공통으로 사용하는 service 만들기


- JDBC 연동 기능 요구사항
- [x] 선택 일정 조회
- [ ] 일정 추가
- [ ] 일정 수정
- [ ] 일정 삭제

---
