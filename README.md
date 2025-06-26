# spring-gift-product

## 상품 API (JSON 기반 REST API)

### 1. 상품 생성

- URL : /api/products
- 메서드 : POST
- 기능 : 상품 생성
- 설명 : 새 상품을 등록한다.

### 2. 상품 조회

- URL : /api/products/{productId}
- 메서드 : GET
- 기능 : 상품 조회
- 설명 : 특정 상품의 정보를 조회한다.

### 3. 상품 수정

- URL : /api/products/{productId}
- 메서드 : PUT
- 기능 : 상품 수정
- 설명 : 기존 상품의 정보를 수정한다.

### 4. 상품 삭제

- URL : /api/products/{productId}
- 메서드 : DELETE
- 기능 : 상품 삭제
- 설명 : 특정 상품을 삭제한다.

## 관리자 화면 (Thymeleaf 기반 웹 페이지)

- Thymeleaf를 기반으로, 관리자가 상품을 등록, 조회, 수정, 삭제할 수 있는 화면을 제공한다.
- 브라우저에서 '/admin/products'로 접속해 사용할 수 있다.

### 1. 상품 등록

- URL : /admin/products/new
- 메서드 : POST
- 기능 : 이름을 입력하여 새 상품을 등록한다.
- 설명 : 등록 후 목록 페이지로 이동

### 2. 상품 목록 조회

- URL : /admin/products
- 메서드 : GET
- 기능 : 등록된 모든 상품을 출력한다.
- 설명 : 각 상품 옆에 '수정', '삭제' 버튼을 함께 표시

### 3. 상품 수정

- URL : /admin/products/{productId}/edit
- 메서드 : GET (수정 폼 화면) / POST (수정 처리)
- 기능 : 기존 상품의 이름을 수정한다.
- 설명 : 수정 후 목록 페이지로 이동

### 4. 상품 삭제

- URL : /admin/products/{productId}/delete
- 메서드 : POST
- 기능 : 선택한 상품을 삭제한다.
- 설명 : 삭제 후 목록 페이지로 이동


