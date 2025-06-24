# spring-gift-product

## 1. 상품 관리

### 1단계. 상품 API 기능 요구 사항

- [X] 상품 단건 조회: 특정 상품의 정보를 조회하는 기능
    - GET /api/products/{productId}
- [ ] 상품 목록 조회: 모든 상품의 목록을 페이지 단위로 조회하는 기능
    - GET /api/products?page=0&size=10&sort=name,asc&categoryId=1
- [X] 상품 등록: 새 상품을 등록한다.
    - POST /api/products
- [X] 상품 삭제: 특정 상품을 삭제한다.
    - DELETE /api/products/{productId}
- [X] 상품 수정: 특정 상품의 정보를 수정한다.
    - PUT /api/products/{productId}

---
