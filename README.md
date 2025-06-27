# spring-gift-product

# step1 리뷰받은 사항 수정하기

## 1. layered architecture

## 2. /product path 공통으로 사용해 코드 중복 줄이기

## 3. thread-safe한 ConcurrentHashMap 사용하기

## 4. Product를 생성할때 덮어씌어지는 상황 해결

## 5. 적절한 HTTP 상태코드 사용하기(201, 204)

# step2 구현

## 상품 목록 조회 GET /admin/products

## 상품 등록 폼  GET /admin/products/new

## 상품 등록 처리 POST /admin/products/new

## 상품 업데이트 폼 GET /admin/products/{id}/update

## 상품 업데이트 처리 POST /admin/products/{id}/update

## 상품 삭제 처리 GET /admin/products/{id}/delete

# step2 리뷰 사항 수정

## 1. 매직넘버를 상수로 선언해 명확하게 나타내기

## 2. delete함수 반환타입 void로 변환

## 3. product를 업데이트할때 파라미터의 id와 ProductRequestDto의 id필드가 일치하지 않는 경우 처리

# step3 구현

## 데이터베이스 연동

## repository 코드 추가하기(상품 조회, 등록, 수정, 삭제)
