# spring-gift-product

## 1️⃣ 프로젝트 소개
- 상품을 조회, 추가, 수정, 삭제할 수 있는 상품 관리 서비스
- 1단계: REST API 기반 JSON 응답
- 2단계: Thymeleaf 이용한 서버 사이드 렌더링 기반 관리자 화면
- 3단계: 기존 메모리 기반 저장소 제거 후 H2 데이터베이스 적용

## 2️⃣ 1단계 구현 예정 기능 목록
- [x] 상품 목록 조회 API (`GET api/products`)
- [x] 상품 단건 조회 API (`GET api/products/{id}`)
- [x] 상품 추가 API (`POST api/products`)
- [x] 상품 전체 수정 API (`PUT api/products/{id}`)
- [x] 상품 삭제 API (`DELETE api/products/{id}`)

## 3️⃣ 2단계 구현 예정 기능 목록 (관리자 화면)
- [x] 관리자 상품 목록 조회 페이지 (`GET admin/products`)
- [x] 관리자 상품 단건 조회 페이지 (`GET admin/products/{id}`)
- [x] 관리자 상품 등록 페이지 및 등록 (`GET/POST admin/products/new`)
- [x] 관리자 상품 수정 페이지 및 수정 (`GET/PUT admin/products/edit/{id}`)
- [x] 관리자 상품 삭제 (`DELETE admin/products/{id}`)

## 4️⃣ 3단계 구현 예정 기능 목록
- [x] H2 DB 설정
- [x] schema.sql, data.sql 작성 및 로드 설정
- [ ] JdbcTemplate 기반의 ProductRepository 구현
- [ ] 서비스 및 컨트롤러에서 DB 기반 Repository로 전환

## 4️⃣ 기술 스택
- Java 17
- Spring Boot 3.5.3
- JSON
- Java Collection
- Thymeleaf
