# spring-gift-product

---

## 기능 요구사항
### 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API 구현 
- HTTP 요청과 응답은 JSON 형식으로 주고 받는다
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임 워크를 사용해 메모리에 저장한다. 

| URL | 메서드 | 기능 | 설명 |
|-----|--------|------|------|
| `/api/products` | POST | 상품 생성 | 새 상품을 등록한다. |
| `/api/products/{productId}` | GET | 상품 조회 | 특정 상품의 정보를 조회한다. |
| `/api/products/{productId}` | PUT | 상품 수정 | 기존 상품의 정보를 수정한다. |
| `/api/products/{productId}` | DELETE | 상품 삭제 | 특정 상품을 삭제한다. |
| `/api/products?page=0&size=10&sort=name,asc&categoryId=1` | GET | 상품 목록 조회 (페이지네이션 적용) | 모든 상품의 목록을 페이지 단위로 조회한다. |

