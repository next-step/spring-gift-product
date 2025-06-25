# 선물하기 상품 관리

## 🛠 1. 상품 API

- [x] 상품 추가 기능 (POST /api/products)
- [x] 상품 목록 조회 기능 (GET /api/products)
- [x] 상품 상세 조회 기능 (GET /api/products/{id})
- [x] 상품 수정 기능 (PUT /api/products/{id})
- [x] 상품 삭제 기능 (DELETE /api/products/{id})

## 🧾 API 명세서

| 기능       | Method | URL                | Request Body                                               | Response                                                                               |
|----------|--------|--------------------|------------------------------------------------------------|----------------------------------------------------------------------------------------|
| 상품 목록 조회 | GET    | /api/products      |                                                            | 200 OK<br>`[{"id": Long, "name": String, "price": Integer, "imageUrl": String}, ... ]` |
| 상품 단건 조회 | GET    | /api/products/{id} |                                                            | 200 OK<br>`{ "id": Long, "name": String, "price": Integer, "imageUrl": String }`       |
| 상품 추가    | POST   | /api/products      | `{ "name": String, "price": Integer, "imageUrl": String }` | 201 Created                                                                            |
| 상품 수정    | PUT    | /api/products/{id} | `{ "name": String, "price": Integer, "imageUrl": String }` | 200 OK                                                                                 |
| 상품 삭제    | DELETE | /api/products/{id} |                                                            | 204 No Content                                                                         |

## 🛠 2. 관리자 화면

📌 상품 목록 (GET /admin/products)

- [x] 서버에서 상품 목록을 조회하여 HTML 테이블로 렌더링한다.
- [x] 각 상품 행마다 수정, 삭제 버튼을 제공한다.
- [x] 페이지 상단에 상품 추가 버튼을 제공한다.

📌 상품 추가 (GET /admin/products/new)

- [x] 폼 화면에서 상품 이름, 가격, 이미지 URL을 입력할 수 있다.
- [x] 등록 버튼 클릭 시 상품이 저장되고 목록 페이지로 이동한다.

📌 상품 수정 (GET /admin/products/edit/{id})

- [ ] 상품 추가 화면과 동일한 폼을 제공한다.
- [ ] 수정 버튼 클릭 시 수정이 반영되고 목록 페이지로 이동한다.

📌 상품 삭제 (POST /admin/products/delete/{id})

- [ ] 삭제 버튼 클릭 시 해당 상품이 삭제된다.
- [ ] 삭제 후 목록 페이지로 리다이렉트한다.
