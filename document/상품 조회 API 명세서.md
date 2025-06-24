# 상품 조회 API 명세서

## 구현 목록

| URL                         | 메서드    | 기능       | 설명                |
|:----------------------------|:-------|----------|-------------------|
| `/api/products/`            | GET    | 다건 상품 조회 | 모든 상품의 정보를 조회합니다. |
| `/api/products/{productId}` | GET    | 단건 상품 조회 | 특정 상품의 정보를 조회합니다. |
| `/api/products/`            | POST   | 단건 상품 생성 | 새로운 상품을 생성합니다.    |
| `/api/products/{productId}` | PUT    | 단건 상품 수정 | 특정 상품의 정보를 수정합니다. |
| `/api/products/{productId}` | DELETE | 단건 상품 삭제 | 특정 상품을 삭제합니다.     |

