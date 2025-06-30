# spring-gift-product

## 1주차 1단계 기능 구현

### 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현

- api 명세서

| URL | 메서드 | 기능 | 설명 |
|-----|--------|------|------|
| `/api/products` | POST | 상품 생성 | 새 상품을 등록한다. |
| `/api/products/{productId}` | GET | 상품 조회 | 특정 상품의 정보를 조회한다. |
| `/api/products/{productId}` | PUT | 상품 수정 | 기존 상품의 정보를 수정한다. |
| `/api/products/{productId}` | DELETE | 상품 삭제 | 특정 상품을 삭제한다. |
| `/api/products?page=0&size=10&sort=name,asc&categoryId=1` | GET | 상품 목록 조회 (페이지네이션 적용) | 모든 상품의 목록을 페이지 단위로 조회한다. |

## 1주차 2단계 기능 구현

### 1단계에서 구현한 API를 사용할 수 있는 관리자 페이지 구현
- Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.

### 구현 상세
- 상품 목록 페이지
- 상품 생성 페이지 및 기능
- 상품 상세 페이지 및 기능
- 상품 수정 페이지 및 기능
- 상품 삭제 기능

## 1주차 3단계 기능 구현

### 데이터 베이스 적용

### 구현 상세
1. 기존 Map을 사용하여 임의로 만든 인메모리 데이터 베이스 제거
2. JDBC Template 적용
3. schema.sql을 작성하여 테이블 생성
4. SQL 쿼리문을 활용하여 CRUD 구현