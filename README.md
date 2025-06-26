# spring-gift-product

## 미션1 상품 관리
___
## STEP1 상품 API

### 기능 요구 사항
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현
- HTTP 요청과 응답은 JSON 형식
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장

### 구현 기능 목록
1. 상품 추가
    - 새 상품 추가
    - 성공적으로 추가되면 201 Created
    - POST /api/products

2. 전체 상품 조회
    - 전체 상품의 정보 반환(조회)
    - GET /api/products

3. 선택 상품 조회
    - 특정 ID에 해당하는 상품의 정보 반환(조회)
    - 성공적으로 조회되면 200 OK
    - 없는 ID면 404 Not Found
    - GET /api/products/{id}

4. 상품 수정
    - 상품 ID에 해당하는 상품의 정보 수정
    - 성공적으로 수정되면 200 OK
    - 없는 ID면 404 Not Found
    - PUT /api/products/{id}

5. 상품 삭제
    - 상품 ID에 해당하는 상품 삭제
    - 성공적으로 삭제되면 204 No Content
    - 없는 ID면 404 Not Found
    - DELETE /api/products/{id}

### 관리자 페이지 구현
1. 상품 목록 페이지
   - /products/view
2. 상품 등록 페이지
   - /product/add
3. 상품 상세 페이지
   - /product/{id}
4. 상품 수정 페이지
   - /product/edit/{id}
5. 상품 삭제는 별다른 페이지 없이
   - /delete/{id}