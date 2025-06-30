# spring-gift-product

## 구현할 기능 목록 (1단계 - 상품 API)

- [x] 상품 목록 조회 API (GET /api/products)
- [x] 상품 단건 조회 API (GET /api/products/{productId})
- [x] 상품 등록 API (POST /api/products)
- [x] 상품 수정 API (PUT /api/products/{productId})
- [x] 상품 삭제 API (DELETE /api/products/{productId})
- [x] JSON 요청 및 응답 포맷 설정
- [x] 메모리 저장소(Map<Long, Product>) 구현

## 구현할 기능 목록 (2단계 - 관리자 화면)

- [x] 관리자 상품 목록 조회 화면
- [x] 상품 등록 화면 및 등록 기능
- [x] 상품 수정 화면 및 수정 기능
- [x] 상품 삭제 기능
-

## 구현할 기능 목록 (3단계 - 데이터베이스 적용)

- [x] H2 데이터베이스 연동 설정 및 콘솔 활성화
- [x] `schema.sql`을 이용한 상품 테이블 자동 생성
- [ ] `ProductRepository`를 JDBC 기반으로 전환
    - [x] 상품 전체 조회 (findAll) 기능 DB 연동
    - [ ] 상품 단건 조회 (findById) 기능 DB 연동
    - [ ] 상품 저장 (save) 기능 DB 연동
    - [ ] 상품 수정 (update) 기능 DB 연동
    - [ ] 상품 삭제 (delete) 기능 DB 연동
- [ ] 기존 메모리 저장소 관련 코드 최종 제거