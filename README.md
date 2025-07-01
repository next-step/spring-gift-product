# spring-gift-product

## API 명세

| HTTP Method | Endpoint         | 설명             | Request Body | Response  |
|-------------|------------------|----------------|-----------|-----------|
| POST | `/products`      | 상품 생성          | `{"name": "상품명", "price": 10000, "imageUrl": "https://example.com/image.jpg"}` | 생성된 상품 id |
| GET | `/products`      | 전체 상품 조회       |  | 상품 목록     |
| GET | `/products/{id}` | 특정 상품 조회       |  | 상품 상세 정보  |
| PUT | `/products/{id}` | 상품 수정          | `{"name": "수정된 상품명", "price": 15000, "imageUrl": "https://example.com/updated-image.jpg"}` | 수정된 상품 정보 |
| DELETE | `/products/{id}` | 상품 삭제          |  | 삭제 완료 메시지 |

## ✅ 요구사항 체크리스트

### [✔️] API 기능 (CRUD)
- [X] 상품 생성 기능
- [X] 전체 상품 조회 기능
- [X] 단건 상품 조회 기능
- [X] 상품 수정 기능
- [X] 상품 삭제 기능

### [✔️] 관리자 페이지 (Thymeleaf 기반)
- [X] 관리자 기초 페이지 구현
- [X] 상품 목록 조회 기능 연동
- [X] 상품 생성 기능 연동
- [X] 상품 수정 기능 연동
- [X] 상품 삭제 기능 연동

### [✔️] DB 마이그레이션 (HashMap -> h2)
- [X] h2 연결 및 목업 데이터 추가
- [X] jdbctemplate 메서드로 기존 코드 리팩토링
    - [X] 게시글 조회 
    - [X] 게시글 생성
    - [X] 게시글 수정
    - [X] 게시글 삭제
- [X] validation 추가 
- [X] db 초기화 및 목업데이터 삽입 로직 위치 변경 (service -> schema/data.sql) 
- [X] Item 객체 사용으로 변경 
- [ ] Optional로 NPE 대응
- [X] Repository 계층 구현
- [ ] 서비스와 리포지토리의 분리
