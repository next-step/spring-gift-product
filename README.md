## 🛒 1단계 - 상품 API

Spring Boot 기반의 간단한 상품 관리 API입니다.  
상품을 **조회, 등록, 수정, 삭제**할 수 있는 RESTful API를 제공합니다.

---

### ✅ 구현할 기능 목록

#### 📌 1. 상품 조회
- [ ] 전체 상품 목록 조회 (GET `/api/products/page=0&size=10&sort=name,asc`)
- [ ] 단일 상품 상세 조회 (GET `/api/products/{id}`)

#### 📌 2. 상품 등록
- [ ] 신규 상품 등록 (POST `/api/products`)
    - 요청 본문에 name, price, description 등 포함

#### 📌 3. 상품 수정
- [ ] 상품 정보 수정 (PUT `/api/products/{id}`)
    - 요청 본문에 수정할 필드 포함 (예: name, price)

#### 📌 4. 상품 삭제
- [ ] 상품 삭제 (DELETE `/api/products/{id}`)

---

### 🧱 향후 확장 고려사항
- [ ] 상품 검색 기능 (카테고리 기준)
- [ ] 상품 품절 상태 관리
- [ ] 등록일 기준 정렬 기능

# 🛒 2단계 - 관리자 화면
Spring Boot와 Thymeleaf 기반의 상품 관리 웹 애플리케이션입니다. 관리자가 **웹 브라우저**를 통해 상품을 **조회, 등록, 수정, 삭제**할 수 있는 관리 시스템을 제공합니다.

---

## ✅ 구현할 기능 목록

### 📌 1. Thymeleaf 환경 설정
* [x] Thymeleaf 의존성 추가
* [x] properties → yml 변환 및 Thymeleaf 관련 설정 추가
* [x] Bootstrap CDN 연동으로 UI 스타일링

### 📌 2. 상품 목록 조회
* [x] 전체 상품 목록 조회 페이지 (GET `/admin/products?page=0&size=10&sort=name,asc`)
  - Pagination 지원
* [x] Bootstrap 테이블로 상품 정보 표시 (이름, 가격, 설명)
* [x] 각 상품별 수정/삭제 버튼 제공
* [x] 상품 등록 버튼 제공

### 📌 3. 상품 등록 기능
* [x] 상품 등록 폼 페이지 (GET `/admin/products/add`)
* [x] 상품 등록 처리 (POST `/admin/products/add`)
  * 요청 데이터: name, price, description
* [x] 입력 데이터 검증 (필수값, 가격 양수 검증 등)
* [x] 등록 성공 시 목록 페이지로 리다이렉트

### 📌 4. 상품 수정 기능
* [x] 상품 수정 폼 페이지 (GET `/admin/products/edit/{id}`)
* [x] 상품 수정 처리 (POST `/admin/products/edit/{id}`)
  * 기존 상품 데이터 폼에 자동 입력
* [x] 수정 성공 시 목록 페이지로 리다이렉트

### 📌 5. 상품 삭제 기능
* [x] 상품 삭제 처리 (POST `/admin/products/delete/{id}`)
* [x] 목록 페이지에서 삭제 버튼 연동
* [x] 삭제 성공 시 목록 페이지로 리다이렉트

## 🧱 향후 확장 고려사항
* 상품 이미지 업로드 기능
* 상품 카테고리 관리 기능
* 페이징 및 정렬 기능 개선
* 상품 품절 상태 관리

## 📊 API 엔드포인트
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/admin/products` | 상품 목록 조회 |
| GET | `/admin/products/add` | 상품 등록 폼 |
| POST | `/admin/products/add` | 상품 등록 처리 |
| GET | `/admin/products/edit/{id}` | 상품 수정 폼 |
| POST | `/admin/products/edit/{id}` | 상품 수정 처리 |
| POST | `/admin/products/delete/{id}` | 상품 삭제 처리 |

# 🧱 3단계 - 데이터베이스 적용

이 단계에서는 기존에 자바 컬렉션 프레임워크로 메모리에 저장하던 상품 정보를 **H2 데이터베이스**에 영구적으로 저장하도록 변경합니다.

---

## ✅ 구현 목표

### 📌 1. H2 데이터베이스 연동
- [x] H2 데이터베이스 의존성 추가
- [x] DataSource 및 H2 설정 구성

### 📌 2. JDBC 기반 Repository 구현
- [x] `JdbcTemplate`과 `SimpleJdbcInsert`를 활용한 `ProductRepository` 구현
- [x] CRUD 기능 완전 구현

### 📌 3. 환경별 Repository 설정 관리
- [x] **`dev` 프로파일 활성화 시 `JdbcProductRepository` 자동 등록**
- [x] 테스트 및 운영 환경에서 Repository 구현체 유연한 교체 지원

### 📌 4. 데이터베이스 스키마 및 초기 데이터
- [x] `resources/schema.sql` 작성:
  - `product` 테이블 생성 스크립트
  - 초기 테스트 데이터 삽입 쿼리

---

## 🔧 주요 구현 포인트

- **프로파일 기반 설정**: 개발/테스트/운영 환경별 Repository 구현체 분리
- **JDBC Template 활용**: Spring의 JDBC 추상화를 통한 안전한 DB 접근
- **스키마 자동 초기화**: 애플리케이션 시작 시 테이블 생성 및 데이터 로드

---

## 🚀 향후 확장 계획
- [ ] Spring Data JPA 기반 Repository 구현체 추가
- [ ] E2E 테스트 케이스 추가
- [ ] CI 파이프라인으로 테스트 자동화
---
