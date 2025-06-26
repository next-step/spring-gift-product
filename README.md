# spring-gift-product

## 프로젝트 소개
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

## STEP1: HTTP API 구현 기능 목록
- 상품 전체 조회 (`GET /api/products`)
- 상품 단건 조회 (`GET /api/products/{id}`)
- 상품 추가 (`POST /api/products`)
- 상품 수정 (`PUT /api/products/{id}`)
- 상품 삭제 (`DELETE /api/products/{id}`)

## STEP2: 관리자 화면 구현
- 관리자 상품 관리 페이지
  - 상품 목록 조회 페이지 (`http://localhost:8080/admin/products`)
  - 상품 추가 페이지 (`http://localhost:8080/admin/products/new`)
  - 상품 수정 페이지 (`http://localhost:8080/admin/products/{id}/edit`)
- 주요 기능
  - 상품 전체 조회 기능
  - 상품 단건 조회 기능
  - 상품 추가 기능
  - 상품 수정 기능
  - 상품 삭제 기능

## 구현 기능 목록
- 상품 전체 조회 (`GET /products`)
- 상품 단건 조회 (`GET /products/{id}`)
- 상품 추가 (`POST /products`)
- 상품 수정 (`PUT /products/{id}`)
- 상품 삭제 (`DELETE /products`)
