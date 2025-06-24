# spring-gift-product

## 상품 api 명세
| URL                         | 메서드 | 기능           | 설명                                          |
|-----------------------------|--------|----------------|-----------------------------------------------|
| `POST /api/products`        | POST   | 상품 생성      | 새 상품을 등록하고 등록된 상품 정보 반환   |
| `GET /api/products`         | GET    | 전체 상품 조회 | 모든 상품의 목록 반환                 |
| `GET /api/products/{id}`    | GET    | 상품 단건 조회 | 경로 변수 `id`에 해당하는 상품 정보 반환   |
| `PUT /api/products/{id}`    | PUT    | 상품 수정      | `id`에 해당하는 상품 수정하고 수정된 정보 반환 |
| `DELETE /api/products/{id}` | DELETE | 상품 삭제      | `id`에 해당하는 상품 삭제             |
