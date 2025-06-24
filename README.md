# 선물하기 상품 관리

## 🛠 STEP 1
- [ ] 상품 추가 기능 (POST /api/products)
- [ ] 상품 목록 조회 기능 (GET /api/products)
- [ ] 상품 상세 조회 기능 (GET /api/products/{id})
- [ ] 상품 수정 기능 (PUT /api/products/{id})
- [ ] 상품 삭제 기능 (DELETE /api/products/{id})

## 🧾 API 명세서

| 기능           | Method | URL                | Request Body                                      | Response                                   |
|----------------|--------|--------------------|-------------------------------------------------|--------------------------------------------|
| 상품 목록 조회  | GET    | /api/products      |                                                 | 200 OK<br>`[{"id": Long, "name": String, "price": int, "imageUrl": String}, ... ]` |
| 상품 단건 조회  | GET    | /api/products/{id} |                                                 | 200 OK<br>`{ "id": Long, "name": String, "price": int, "imageUrl": String }`      |
| 상품 추가       | POST   | /api/products      | `{ "name": String, "price": int, "imageUrl": String }` | 201 Created                               |
| 상품 수정       | PUT    | /api/products/{id} | `{ "name": String, "price": int, "imageUrl": String }` | 200 OK                                    |
| 상품 삭제       | DELETE | /api/products/{id} |                                                 | 204 No Content                            |
