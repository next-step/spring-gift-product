# spring-gift-product

 step 1. 상품 조회/추가/수정/삭제 api 구현

 1. 프로젝트 구조화 및 Product Entity 생성
 2. 상품 추가 구현
 3. 상품 조회 구현
 4. 상품 수정 구현
 5. 상품 삭제 구현
 6. 버그 수정 및 리팩토링

|api|method|기능|완료 여부|
|-----|-----|-----|-----|
|api/product|GET|상품 전체 조회|완료|
|api/product/{id}|GET|특정 상품 조회|완료|
|api/product|POST|상품 추가 (request body 요구)|완료|
|api/product/{id}|PUT|상품 수정 (Entity 단위 교체, request body 요구)|완료|
|api/product/{id}|PATCH|상품 수정 (일부 내용 교체, request body 요구)|완료|
|api/product/{id}|DELETE|상품 삭제|완료|