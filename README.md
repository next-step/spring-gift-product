# spring-gift-product

## Step 1 - 상품 API

---

#### 기능 요구 사항

- HTTP 요청과 응답은 JSON 형식으로 주고받음
- 별도 DB가 없으므로 Map을 사용하여 메모리에 저장

<br>

#### API 명세 

| URL                  | 메서드  | 기능    | 설명           |
|----------------------|------|-------|--------------|
| /api/products        | POST | 상품 생성 | 새 상품 등록      |
| /api/products/{productId} | GET  | 상품 조회 | 특정 상품 정보 조회  |
| /api/products/{productId} | PUT  | 상품 수정 | 기존 상품 정보 수정  |
| /api/products/{productId} | DELETE | 상품 삭제 | 특정 상품 삭제     |
| /api/products | GET | 상품 전체 조회 | 모든 상품 정보 조회  |