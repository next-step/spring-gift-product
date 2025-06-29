# spring-gift-product

## API

### Item CRUD API

| 기능       | 메소드    | URL                   | 반환데이터                           |
|----------|--------|-----------------------|---------------------------------|
| 상품 목록 조회 | GET    | `/api/items`          | `List<ItemResponseDto>`         |
| 특정 상품 조회 | GET    | `/api/items/{itemId}` | `ItemResponseDto`               |
| 상품 생성    | POST   | `/api/items`          | `ItemResponseDto` (201 Created) |
| 상품 수정    | PUT    | `/api/items/{itemId}` | `ItemResponseDto`               |
| 상품 삭제    | DELETE | `/api/items/{itemId}` | `void` (204 No Content)         |

### 관리자 페이지 API

| 기능          | 메소드    | URL                        | 반환데이터                     |
|-------------|--------|----------------------------|---------------------------|
| 관리자 메인 페이지  | GET    | `/admin/items`             | `admin-item.html` (상품 목록) |
| 상품 생성 폼 페이지 | GET    | `/admin/items/new`         | `admin-item-new.html`     |
| 상품 수정 폼 페이지 | GET    | `/admin/items/update/{id}` | `admin-item-update.html`  |
| 상품 생성 처리    | POST   | `/admin/items`             | `redirect:/admin/items`   |
| 상품 수정 처리    | PUT    | `/admin/items/{id}`        | `redirect:/admin/items`   |
| 상품 삭제 처리    | DELETE | `/admin/items/{id}`        | `redirect:/admin/items`   |

## 구현 기능

### step1 구현 기능

- [x] 상품 entity 구현
- [x] 단일 상품 조회 API 구현
- [x] 전체 상품 조회 API 구현
- [x] 상품 생성 API 구현
- [x] 상품 수정 API 구현
- [x] 상품 삭제 API 구현

### step2 구현 기능

- [x] 관리자 페이지
    - [x] 상품 전체 조회 (메인)페이지 API 구현
    - [x] 상품 생성 페이지 API 구현
    - [x] 상품 업데이트 페이지 API 구현
    - [x] 상품 삭제 버튼 구현
- [x] 요청 form 처리 API
    - [x] 상품 생성 API
    - [x] 상품 업데이트 API
    - [x] 상품 삭제 API

### step3 구현 기능

- [x] DB 사용 위한 설정
- [x] DB 테이블 생성 및 초기 데이터 삽입
- [x] DB기반 Repository 구현
- [ ] 메모리 저장소에서 DB 저장소로 변경

### 추가 구현 기능

- [ ] 입력값 검증
- [x] null 예외처리
- [x] 전역 예외처리