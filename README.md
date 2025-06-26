# spring-gift-product

# 1단계: 상품 API 구현

## 기능 목록

- [x] 상품 클래스(Entity) 구현
- [x] 요청, 응답 DTO 생성
- [x] Controller, Service, Repository 레이어 생성
- [x] 상품 전체 조회 기능
    - `GET /api/products`
- [x] 상품 단일 조회 기능
    - `GET /api/products/{id}`
- [x] 상품 추가 기능
    - `POST /api/products`
- [x] 상품 수정 기능
    - `PUT /api/products/{id}`
- [x] 상품 삭제 기능
    - `DELETE /api/products/{id}`

## API 명세
| 기능 | HTTP Method | URL | Request Body | 응답 (코드 및 Body) |
| :--- | :--- | :--- | :--- | :--- |
| **상품 전체 조회** | `GET` | `/api/products` | 없음 | **성공**: `200 OK` (`List<ProductResponseDto>`) |
| **상품 단일 조회** | `GET` | `/api/products/{id}` | 없음 | **성공**: `200 OK` (`ProductResponseDto`) <br> **실패**: `404 Not Found` (해당 id의 상품이 없을 때) |
| **상품 추가** | `POST` | `/api/products` | `ProductRequestDto` | **성공**: `201 Created` (`ProductResponseDto`) <br> **실패**: `400 Bad Request` (요청 형식이 잘못됐을 때) |
| **상품 수정** | `PUT` | `/api/products/{id}` | `ProductRequestDto` | **성공**: `200 OK` (`ProductResponseDto`) <br> **실패**: `404 Not Found` (해당 id의 상품이 없을 때) |
| **상품 삭제** | `DELETE`| `/api/products/{id}` | 없음 | **성공**: `204 No Content` (Body 없음) <br> **실패**: `404 Not Found` (해당 id의 상품이 없을 때) |

# 2단계: 관리자 화면

## 기능 목록

- [x] **상품 목록 조회**
  - `feat`: Controller에서 Thymeleaf로 전달한 DTO인 ProductView 생성 (`ProductView`)
  - `feat`: 상품 목록 조회 컨트롤러 로직 구현 (`GET /admin/products`)
  - `feat`: 상품 목록 조회 HTML 템플릿 작성 (`admin/products.html`)
  
- [x] **상품 추가 기능**
  - `feat`: 상품 추가 폼 페이지로 이동하는 컨트롤러 로직 구현 (`GET /admin/products/add`)
  - `feat`: 상품 추가 HTML 템플릿 작성 (`admin/add-form.html`)
  - `feat`: 상품 추가 데이터 처리 컨트롤러 로직 구현 (`POST /admin/products/add`)