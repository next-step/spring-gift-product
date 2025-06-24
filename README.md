# spring-gift-product

1. 상품 조회, 추가, 수정, 삭제 HTTP API 구현

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

상품 API

|URL|메서드|기능|설명|
|--|--|--|--|
|/api/products|POST|상품 생성|새 상품 등록|
|/api/products/{productId}|GET|상품 단건 조회|특정 상품의 정보 조회|
|/api/products|GET|상품 전체 조회|모든 상품의 정보 조회|
|/api/products/{productId}|PUT|상품 수정|기존 상품의 정보 수정|
|/api/products/{productId}|DELETE|상품 단건 삭제|특정 상품 삭제|
|/api/products|DELETE|상품 전체 삭제|모든 상품 삭제|