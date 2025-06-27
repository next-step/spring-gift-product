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

# 🛒 2단계 - 상품 관리 웹 애플리케이션
Spring Boot와 Thymeleaf 기반의 상품 관리 웹 애플리케이션입니다. 관리자가 **웹 브라우저**를 통해 상품을 **조회, 등록, 수정, 삭제**할 수 있는 관리 시스템을 제공합니다.

## ✅ 구현할 기능 목록

### 📌 1. Thymeleaf 환경 설정
* [x] Thymeleaf 의존성 추가
* [x] properties → yml 변환 및 Thymeleaf 관련 설정 추가
* [x] Bootstrap CDN 연동으로 UI 스타일링

### 📌 2. 상품 목록 조회
* [x] 전체 상품 목록 조회 페이지 (GET `/admin/products`)
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

## 🔄 커밋 히스토리
1. **feat: Thymeleaf 의존성 추가**
2. **config: properties→yml 변환 및 Thymeleaf 설정 추가**
3. **feat: 상품 등록 API & 폼 개발**
4. **feat: 상품 수정 API & 폼 개발**
5. **feat: 상품 삭제 API 개발**

