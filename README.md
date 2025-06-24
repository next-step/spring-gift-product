# spring-gift-product

- 1단계 - 상품 API 기능 요구 사항

| 기능명      | Method | URL                              | Request                 | Response                    | 상태코드             |
| -------- | ------ | -------------------------------- |-------------------------| --------------------------- | ---------------- |
| 전체 상품 조회 | GET    | `/api/products`              | 없음                      | 다건 응답 정보 | `200 OK`         |
| 상품 단건 조회 | GET    | `/api/products/{id}` | Path Variable           | 단건 응답 정보       | `200 OK`         |
| 상품 추가    | POST   | `/api/products`              | 요청 body                 | 등록 정보       | `201 Created`    |
| 상품 수정    | PATCH  | `/api/products/{id}` | 요청 body + Path Variable | 수정 정보       | `200 OK`         |
| 상품 삭제    | DELETE | `/api/products/{id}` | 요청 body + Path Variable | 없음                          | `204 No Content` |
