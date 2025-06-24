# spring-gift-product

## API 명세

### 상품 API

| URL                       | 메서드    | 기능       | 설명           |
|---------------------------|--------|----------|--------------|
| /api/products             | POST   | 상품 생성    | 새 상품 등록      |
| /api/products/{productId} | GET    | 상품 조회    | 특정 상품 정보 조회  |
| /api/products/{productId} | PUT    | 상품 수정    | 기존 상품 정보 수정  |
| /api/products/{productId} | DELETE | 상품 삭제    | 특정 상품 삭제     |
| /api/products             | GET    | 상품 목록 조회 | 모든 상품의 목록 조회 |

