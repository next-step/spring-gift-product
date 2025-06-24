# spring-gift-product

## STEP1 상품 API 명세

- 모든 상품의 목록을 JSON 형식으로 반환한다.

1. **상품 추가 (POST /api/products)**
   - 상품 정보를 요청 본문에 담아 새로운 상품을 추가한다.
   - 성공적으로 추가되면 201 Created 상태 코드와 함께 새로 추가된 상품의 정보 반환.

2. **상품 수정 (PUT /api/products/{id})**
   - 상품 ID에 해당하는 상품의 정보를 수정한다.
   - 성공적으로 수정되면 200 OK 상태 코드와 함께 수정된 상품 정보 반환.

3. **상품 삭제 (DELETE /api/products/{id})**
   - 상품 ID에 해당하는 상품을 삭제한다.
   - 성공적으로 삭제되면 204 No Content 상태 코드 반환.

4. **상품 조회 (GET /api/products/{id})**
   - 특정 ID에 해당하는 상품의 상세 정보를 반환한다.