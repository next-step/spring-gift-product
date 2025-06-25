# spring-gift-product

## 1️⃣ 프로젝트 소개
- 상품을 조회, 추가, 수정, 삭제할 수 있는 상품 관리 서비스
- 1단계: REST API 기반 JSON 응답
- 2단계: Thymeleaf 이용한 서버 사이드 렌더링 기반 관리자 화면

## 2️⃣ 1단계 구현 예정 기능 목록
- [x] 상품 목록 조회 API (`GET api/products`)
- [x] 상품 단건 조회 API (`GET api/products/{id}`)
- [x] 상품 추가 API (`POST api/products`)
- [x] 상품 전체 수정 API (`PUT api/products/{id}`)
- [x] 상품 일부 수정 API (`PATCH api/products/{id}`)
- [x] 상품 삭제 API (`DELETE api/products/{id}`)

## 3️⃣ 2단계 구현 예정 기능 목록 (관리자 화면)
- [x] 상품 목록 조회 페이지 (`GET admin/products`)
- [x] 상품 단건 조회 페이지 (`GET admin/products/{id}`)
- [x] 상품 등록 페이지 및 등록 (`POST admin/products`)
- [ ] 상품 전체 수정 (`PUT admin/products/{id}`)
- [ ] 상품 삭제 (`DELETE admin/products/{id}`)

## 4️⃣ 기술 스택
- Java 17
- Spring Boot 3.5.3
- JSON
- Java Collection
- Thymeleaf
