# spring-gift-product

---

## Step 1: 구현할 기능 목록

* [X] 모든 상품 조회 (GET /api/products)
* [X] 특정 상품 조회 (GET /api/products/{id})
* [X] 상품 추가 (POST /api/products)
* [X] 상품 수정 (PUT /api/products/{id})
* [X] 상품 삭제 (DELETE /api/products/{id})

## Step 2: 구현할 기능 목록

* [X] 상품 목록 조회 화면: 모든 상품을 테이블 형태로 제공 & 각 상품의 상세 조회, 수정, 삭제를 위한 UI 제공
    * `GET /admin/products`
* [X] 상품 추가 화면: 새로운 상품 정보를 입력 폼 제공 & 실제 상품 추가 기능 구현
    * `GET /admin/products/new` (상품 추가 폼 페이지)
    * `POST /admin/products` (폼 제출 후 상품 추가 처리)
* [ ] 상품 수정 화면: 특정 상품 정보 수정 폼 제공
    * `GET /admin/products/{id}/edit` (상품 수정 폼 페이지)
    * `PUT /admin/products/{id}` (폼 제출 후 상품 수정 처리)
* [ ] 상품 삭제 기능: 특정 상품 삭제 기능 구현
    * `DELETE /admin/products/{id}`