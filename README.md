# spring-gift-product

구현 기능 목록 정리
1. 전체 상품을 조회한다(GET /api/products) - 완료 
2. 단건 상품을 조회한다(GET /api/products/{id}) - 완료
3. 상품을 추가한다(POST /api/products)
4. 상품을 수정한다(PUT /api/products/{id})
5. 상품을 삭제한다(DELETE /api/products/{id})

위 목록을 HTTP API 구현해 CRUD가 작동하게 한다
--> HTTP 요청과 응답은 JSON형식
--> 현재는 자바 컬렉션 프레임 워크를 사용해 메모리에 저장

설계 및 작성 순서는
entity -> dto -> repo ->  service -> controller
로 생각하면서 작성