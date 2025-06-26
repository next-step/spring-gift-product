# spring-gift-product

## API 명세

| HTTP Method | Endpoint         | 설명             | Request Body | Response  |
|-------------|------------------|----------------|-----------|-----------|
| POST | `/products`      | 상품 생성          | `{"name": "상품명", "price": 10000, "imageUrl": "https://example.com/image.jpg"}` | 생성된 상품 id |
| GET | `/products`      | 전체 상품 조회       |  | 상품 목록     |
| GET | `/products/{id}` | 특정 상품 조회       |  | 상품 상세 정보  |
| PUT | `/products/{id}` | 상품 수정          | `{"name": "수정된 상품명", "price": 15000, "imageUrl": "https://example.com/updated-image.jpg"}` | 수정된 상품 정보 |
| DELETE | `/products/{id}` | 상품 삭제          |  | 삭제 완료 메시지 |

## ✅ 요구사항 체크리스트

### [✔️] API 기능 (CRUD)
- [✔️] 상품 생성 기능
- [✔️] 전체 상품 조회 기능
- [✔️] 단건 상품 조회 기능
- [✔️] 상품 수정 기능
- [✔️] 상품 삭제 기능

### [ ] 관리자 페이지 (Thymeleaf 기반)
- [✔️] 관리자 기초 페이지 구현
- [✔️] 상품 목록 조회 기능 연동
- [✔️] 상품 생성 기능 연동
- [✔️] 상품 수정 기능 연동
- [ ] 상품 삭제 기능 연동