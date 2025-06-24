# spring-gift-product

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

---
## 구현할 기능 목록

### 1. 상품 조회
- 전체 상품 목록 조회: `GET /products`
- 특정 상품 목록 조회: `GET /products/{id}`

### 2. 상품 추가
- 새로운 상품 추가: `POST /products`

### 3. 상품 수정
- 기존 상품 수정: `PUT /products/{id}`

### 4. 상품 삭제
- 특정 상품 삭제: `DELETE /products/{id}`