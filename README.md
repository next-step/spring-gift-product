# spring-gift-product

## 1. 상품 관리

### 1단계. 상품 API 기능 요구 사항

- [X] 상품 단건 조회: 특정 상품의 정보를 조회하는 기능
    - GET /api/products/{productId}
    - **세부사항**: 상품이 존재하지 않을 경우 '404 Not Found' 응답
- [X] 상품 목록 조회: 모든 상품의 목록을 페이지 단위로 조회하는 기능
    - GET /api/products?page=0&size=10&sort=name,asc&categoryId=1
    - **세부사항**:
        - 'page': 페이지 번호 (기본값 0으로 설정)
        - 'size': 페이지당 상품 수 (기본값 10으로 설정)
        - 'sort': 정렬 기준 (예시: 'name, asc' / 'price, desc')
        - 'categoryId': 특정 카테고리로 필터링 (현재 구현 범위에서는 미포함, 이후 미션에서 수행할 예정)
- [X] 상품 등록: 새 상품을 등록한다.
    - POST /api/products
    - **세부사항**:
        - 요청 및 응답은 JSON 형식
        - 성공 시 '201 Created' 응답 및 'Location' 헤더에 생성된 리소스 URI 포함
        - 유효성 검사(이름 공백, 음수 가격) 실패 시 '400 Bad Request' 응답.
        - 이미 존재하는 ID로 등록 시도 시 '409 Conflict' 응답
- [X] 상품 삭제: 특정 상품을 삭제한다.
    - DELETE /api/products/{productId}
    - **세부사항**:
        - 성공 시 '204 No Content' 응답
        - 상품이 존재하지 않을 경우 '404 Not Found' 응
- [X] 상품 수정: 특정 상품의 정보를 수정한다.
    - PUT /api/products/{productId}답
    - **세부사항**:
        - 요청 및 응답은 JSON 형식
        - 성공 시 '200 OK' 응답
        - 유효성 검사 실패 시 '400 Bad Request' 응답
        - 상품이 존재하지 않을 경우 '404 Not Found' 응답

### 구현 제약 사항

- 현재는 별도의 데이터베이스 없이 Map을 사용하여 메모리에 상품 데이터를 저장한다. 즉, 애플리케이션 재시작 시 데이터는 초기화된다.

---
