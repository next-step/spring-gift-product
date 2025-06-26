# spring-gift-product
## 1단계

1. 상품 추가 기능
POST /api/products

2. 특정 상품 조회 기능
GET /api/products/{productId}

3. 모든 상품 조회 기능
상품 목록 조회 (페이지네이션 적용)	모든 상품의 목록을 페이지 단위로 조회한다(추후 예정)
GET	/api/products?page=0&size=10&sort=name,asc&categoryId=1	

4. 상품 수정 기능
PUT	/api/products/{productId}

5. 상품 삭제 기능
DELETE /api/products/{productId}

## 2단계


1. 관리자 상품 추가 기능

2. 관리자 상품 추가 뷰 

3. 관리자 특정 상품 조회 기능

4. 관리자 모든 상품 조회 기능
상품 목록 조회 (페이지네이션 적용)	모든 상품의 목록을 페이지 단위로 조회한다(추후 예정)

5. 관리자 상품 수정 기능

6. 관리자 상품 삭제 기능