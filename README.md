# spring-gift-product

## step 1 상품 API  
### 기능 요구 사항 정리

---

### 상품 목록 조회 API  
- `GET /products`  
  → `200 OK`: 상품 리스트 반환  

---

### 단일 상품 조회 API  
- `GET /products/{id}`  
  → `200 OK`: 상품 정보 반환  
  → `404 Not Found`: 해당 상품 없음  

---

### 상품 등록 API  
- `POST /products`  
  → `201 Created`: 상품 생성 완료  
  → `400 Bad Request`: 잘못된 요청 데이터  

---

### 상품 수정 API  
- `PUT /products/{id}`  
  → `204 No Content`: 수정 성공, 응답 본문 없음  
  → `404 Not Found`: 수정할 상품 없음  

---

### 상품 삭제 API  
- `DELETE /products/{id}`  
  → `204 No Content`: 삭제 성공  
  → `404 Not Found`: 삭제할 상품 없음

