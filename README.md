# spring-gift-product

## step 1 상품 API  
### 상품 목록 조회 API  
- GET /products  
  → 200 OK : 상품 리스트 반환  

### 단일 상품 조회 API  
- GET /products/{id}
  → 200 OK: 상품 정보 반환  
  → 404 Not Found: 해당 상품 없음  

### 상품 등록 API  
- POST /products 
  → 201 Created: 상품 생성 완료  
  → 400 Bad Request: 잘못된 요청 데이터  

### 상품 수정 API  
- PUT /products/{id}  
  → 204 No Content: 수정 성공, 응답 본문 없음  
  → 404 Not Found: 수정할 상품 없음  

### 상품 삭제 API  
- DELETE /products/{id}
  → 204 No Content : 삭제 성공  
  → 404 Not Found : 삭제할 상품 없음

## Step 2 - 관리자 상품 관리 화면

이미 구현된 상품 CRUD API를 기반으로, 관리자 전용 UI를 서버 사이드 렌더링 방식으로 구현한다.

### 구현할 관리자 화면 기능 목록

- 관리자 상품 목록 조회 화면 구현
- 관리자 상품 등록 화면 구현
- 관리자 상품 등록 기능 구현
- 관리자 상품 상세 조회 화면 구현
- 관리자 상품 수정 화면 구현
- 관리자 상품 수정 기능 구현
- 관리자 상품 삭제 기능 구현

## Step 3 - 상품 정보 DB 연동
- 메모리에 저장하던 상품 정보를 H2 데이터베이스에 저장하도록 변경



