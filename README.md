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
- [x] API 입력값 유효성 검증(Validation) 및 예외 처리 추가
  - refactor: `ProductRequestDto`에 유효성 검사 어노테이션 적용
  - refactor: 상품 추가/수정 Controller 메서드에 `@Valid` 적용

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

- [x] 상품 목록 조회
  - `feat`: Controller에서 Thymeleaf로 전달한 DTO인 ProductView 생성 (`ProductView`)
  - `feat`: 상품 목록 조회 컨트롤러 로직 구현 (`GET /admin/products`)
  - `feat`: 상품 목록 조회 HTML 템플릿 작성 (`admin/products.html`)
  
- [x] 상품 추가 기능
  - `feat`: 상품 추가 폼 페이지로 이동하는 컨트롤러 로직 구현 (`GET /admin/products/add`)
  - `feat`: 상품 추가 HTML 템플릿 작성 (`admin/add-form.html`)
  - `feat`: 상품 추가 데이터 처리 컨트롤러 로직 구현 (`POST /admin/products/add`)

- [x] 상품 수정 기능
  - `feat`: 상품 수정 폼 페이지로 이동하는 컨트롤러 로직 구현 (`GET /admin/products/edit/{id}`)
  - `feat`: 상품 수정 폼 페이지 HTML 템플릿 작성 (`admin/edit-form.html`)
  - `feat`: 상품 수정 데이터 처리 컨트롤러 로직 구현 (`POST /admin/products/edit/{id}`)
  - 
- [x] 상품 삭제 기능
  - `feat`: 상품 삭제 처리 컨트롤러 로직 구현 (`POST /admin/products/delete/{id}`)

# 3단계: 데이터베이스 적용

## 기능 목록

- [x] H2 데이터베이스 연동 설정
  - `feat`: `application.properties`에 연결 정보 설정
- [x] 상품 테이블 스키마
  - `feat`: `schema.sql` 추가
- [x] ProductRepository 데이터베이스 적용
  - `refactor`: 데이터저장소를 HashMap 기반에서 JdbcClient 기반으로 전환
  - `feat`: `RowMapper<Product>`를 반환하는 `getProductRowMapper()` 추가
  - `refactor`: id 값에 따라 `Optional<Product>`를 반환하는 `findById(Long id)` 리펙토링
  - `refactor`: 상품 전체 리스트를 반환하는 `findAll()` 리펙토링
  - `refactor`: id 값에 해당하는 상품을 삭제하는 `deleteById(Long id)` 리펙토링
  - `refactor`: 상품 객체를 데이터 저장소에 저장하는 `save(Product product)` 리펙토링
  - `refactor`: 상품을 수정하는 `update(Product product)` 리펙토링(추가) && 기존 관련 메서드 삭제
- [x] 상품 초기화
  - `feat`: `data.sql` 추가