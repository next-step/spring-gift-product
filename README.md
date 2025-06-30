# **spring-gift-product**

***

## 1️⃣ step1: 상품 API

- [x] 상품 생성
- [x] 상품 단건 조회
- [x] 상품 수정
- [x] 상품 삭제
- [x] 상품 목록 조회 *(기본 기능만 구현됨, 페이지네이션 미구현)*

## 2️⃣ step2: 관리자 화면

- [x] 상품 목록 조회
- [x] 상품 단건 조회
- [x] 상품 추가
- [x] 상품 수정
- [x] 상품 삭제

## 3️⃣ step3: 데이터베이스 적용

- [ ] 데이터베이스 설정
    - [x] H2 데이터베이스 연동 설정
    - [ ] schema.sql 파일로 product 테이블 구조 정의
    - [ ] data.sql 파일로 초기 데이터 삽입
- [ ] JdbcClient를 활용한 DB 연동 구현
    - [ ] Map 기반 임시 저장소 제거
    - [ ] JdbcClient를 통한 DB 접근 구현
    - [ ] SQL 기반 CRUD 로직 적용
    - [ ] RowMapper를 통해 Product 객체 반환 처리