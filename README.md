# spring-gift-product

### 상품관리 - 1단계 기능 목록

- [x] Entity 설계
- [x] Dto 설계
- [x] 메모리 기반 Repository 설계
- [x] Service 설계
- [x] 상품 추가 API 설계
- [x] 상품 단건 조회 API 설계
- [x] 상품 전체 조회 API 설계
- [x] 상품 수정 API 설계
- [x] 상품 삭제 API 설계

### 상품관리 - 2단계 기능 목록

- [x] 관리자 화면 Controller 설계 - 상품 추가, 목록 조회, 수정, 삭제
- [x] 상품 목록 조회 html 작성
- [x] 상품 추가 html 작성
- [x] 상품 수정 html 작성

### 상품관리 - 1, 2단계 피드백 반영 목록

- [x] 변수 네이밍 수정 (MemoryProductRepository의 productMap -> products)
- [x] 패키지 이름 변경 entity -> domain
- [x] 매직 넘버 상수화 (MemoryProductRepository)
- [x] Product의 setter, 기본 생성자 사용 지양에 따른 ProductServiceImpl의 createProduct 수정

### 상품관리 - 3단계 기능 목록

- [x] application.properties 세팅
- [x] schema, 초기 데이터 세팅 sql 작성
- [x] 기존 MemoryProductRepository 어노테이션 제거
- [x] H2 데이터베이스 기반 repository 구현
