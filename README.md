# spring-gift-product

## API 명세

### 상품관리 RESTful API

| 기능 | HTTP Method | 엔드포인트 (Endpoint) | 요청 (Request) | 응답 (Response) |
| :--- | :--- | :--- | :--- | :--- |
| 상품 등록 | `POST` | `/api/products` | Body: `ProductRequestDto` (상품 정보) | **201 Created** Body 없음 |
| 전체 상품 조회 | `GET` | `/api/products` | 없음 | **200 OK** Body: `List<ProductResponseDto>` (상품 목록) |
| 특정 상품 조회 | `GET` | `/api/products/{productId}` | Path: `productId` | **200 OK** Body: `ProductResponseDto` (상품 상세 정보) |
| 상품 정보 수정 | `PUT` | `/api/products/{productId}` | Path: `productId` Body: `ProductRequestDto` (수정할 상품 정보) | **204 No Content** Body 없음 |
| 상품 삭제 | `DELETE` | `/api/products/{productId}` | Path: `productId` (상품 ID) | **204 No Content** Body 없음 |

### 상품관리 관리자 View
| 기능 | HTTP Method | 엔드포인트 (Endpoint) | 설명 |
| :--- | :--- | :--- | :--- |
| 상품 목록 조회 | `GET` | `/admin/product` | 전체 상품 목록 조회 |
| 상품 상세 조회 | `GET` | `/admin/product/{productId}` | 상품 상세 정보 조회 |
| 상품 추가 | `POST` | `/admin/product/add` | 새 상품 등록 |
| 상품 수정 | `POST` | `/admin/product/edit/{productId}` | 상품 정보 수정 |
| 상품 삭제 | `DELETE` | `/admin/product/{productId}` | 상품 삭제 |

## 상품관리 - 스프링 입문

### 1단계 - 상품 API

#### 기능 목록

- Product 도메인 구현
- Product DTO 구현
- Product Service 구현
- Product Controller 구현
- 공통 예외 처리 구현 (NOT_FOUND)

### 1단계 - 코드 리뷰 반영

- 불필요한 주식 제거
- 테스트용 코드 제거
- 규칙 9: getter/setter/프로퍼티를 쓰지 않는다 - Product setter 제거
- service 계층의 id, Map 객체를 repository 계층으로 이동

### 2단계 - 관리자 화면

- 상품 리스트 조회 페이지 구현
- 상품 추가 페이지 구현
- 상품 상세 페이지 구현
- 상품 수정 & 삭제 페이지 구현