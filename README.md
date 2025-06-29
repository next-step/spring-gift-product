# spring-gift-product

## 구현할 기능 목록

- [x] 상품 조회 API 구현
- [x] 상품 등록 API 구현
- [x] 상품 수정 API 구현
- [x] 상품 삭제 API 구현
- [x] 상품 목록 페이지네이션 조회 API 구현


## 구현 기능 상세

### ✅ 상품 조회 API (`GET /api/products/{id}`)
- 상품 ID를 통해 개별 상품 정보를 조회할 수 있는 API
- 응답 형식: JSON
- 응답 예시:
```json
{
  "id": 1,
  "name": "아이스 아메리카노",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_..."
}
```
### ✅ 상품 등록 API (`POST /api/products`)
- 새로운 상품을 등록하는 API
- 요청 Body: JSON 형식의 상품 정보 (name, price, imageUrl)
- 응답: 생성된 상품 객체와 자동 생성된 id
- 응답 예시:
```json
{
  "id": 2,
  "name": "뜨거운 아메리카노",
  "price": 4000,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20240101120000_hotamericano.jpg"
}
```
### ✅ 상품 수정 API (`PUT /api/products/{id}`)
- 특정 ID의 상품 정보를 수정하는 API
- 요청 Body: JSON 형식의 수정할 상품 정보 (`name`, `price`, `imageUrl`)
- 기존 상품이 존재할 경우에만 수정됨
- 응답 예시:
```json
{
  "id": 1,
  "name": "수정된 아메리카노",
  "price": 4800,
  "imageUrl": "https://example.com/updated.jpg"
}
```
### ✅ 상품 삭제 API (`DELETE /api/products/{id}`)
- 특정 ID의 상품을 삭제하는 API
- 응답 본문 없이 상태 코드만 반환함
- 상태 코드:
    - `204 No Content`: 삭제 성공
    - `404 Not Found`: 해당 ID의 상품이 존재하지 않을 경우

### ✅ 상품 목록 페이지네이션 조회 API (`GET /api/products?page=0&size=10`)
- 전체 상품 목록을 페이징 처리하여 조회하는 API
- 쿼리 파라미터:
    - `page`: 페이지 번호 (0부터 시작, 기본값: 0)
    - `size`: 페이지당 항목 수 (기본값: 10)
- 응답 예시:
```json
[
  {
    "id": 1,
    "name": "아이스 아메리카노",
    "price": 4500,
    "imageUrl": "https://..."
  },
  {
    "id": 2,
    "name": "뜨거운 아메리카노",
    "price": 4000,
    "imageUrl": "https://..."
  }
]
```
---
## Step2 - 관리자 상품 화면

### 구현 목표

- 관리자 전용 상품 관리 화면 구현
- 상품 목록, 등록, 조회, 수정, 삭제 기능 제공
- Thymeleaf를 이용한 서버 사이드 렌더링 적용

---

### 기능 목록 및 구현 계획

- [x] **상품 목록 조회 화면 구현 (페이징, 정렬, 필터 포함)**
  - `/admin/products` 경로로 진입 시 상품 목록 표시
  - `ProductResponseDto` 목록을 전달하여 Thymeleaf로 렌더링
  - 페이지네이션: page, size, sort, categoryId 쿼리 파라미터 사용
  - 페이지 이동 링크 및 정렬 기준(이름, 가격 등) 선택 가능하도록 구현

- [x] **상품 등록 화면 구현**
  - `/admin/products/new` GET → 등록 폼 렌더링
  - POST 요청 → `ProductRequestDto`를 통해 서버로 제출
  - 등록 성공 시 목록 페이지로 리다이렉트

- [x] **상품 수정 화면 구현**
  - `/admin/products/{productId}/edit` GET → 기존 값이 입력된 폼 렌더링
  - POST 요청 → `ProductRequestDto`로 수정된 값 제출
  - 수정 성공 시 상세 페이지 또는 목록 페이지로 리다이렉트

- [x] **상품 삭제 기능 구현**
  - 상세 페이지에서 삭제 버튼 클릭 시 `/admin/products/{productId}/delete`
  - POST 또는 DELETE 요청 처리 → 목록 페이지로 리다이렉트
  - 삭제 전 사용자 확인(alert 또는 모달)

- [x] **Thymeleaf를 통한 서버 사이드 렌더링 적용**
  - HTML 페이지 렌더링에 Thymeleaf 템플릿 엔진 사용
  - `${}` 문법으로 Dto 데이터를 바인딩하여 출력
  - form 태그와 `th:action`, `th:field`, `th:each` 등 활용
